package com.example.Heunduljang.common.filter;

import com.example.Heunduljang.common.error.ErrorCode;
import com.example.Heunduljang.common.exception.BadRequestException;
import com.example.Heunduljang.common.exception.UnauthorizedException;
import com.example.Heunduljang.user.service.CustomUserDetailService;
import com.example.Heunduljang.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

//@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends BasicAuthenticationFilter {

    private final CustomUserDetailService customUserDetailService;
    private final UserService userService;


    public AuthenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailService customUserDetailService, UserService userService) {
        super(authenticationManager);
        this.customUserDetailService = customUserDetailService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        /**
         * 아이디가 존재시
         */
        String appleId = getAppleId(request);
        log.info("appleId: {}", appleId);
        if(!appleId.isEmpty()){
            // 애플 아이디 검증
            if(userService.isValidAppleId(appleId)){
                Authentication authentication = getAuthentication(appleId);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                HashMap<String,String> map = new HashMap<>();
                map.put("appleId", appleId);
                throw new UnauthorizedException(ErrorCode.NOT_VALID_TOKEN,map);
            }

        }else{
            throw new BadRequestException(ErrorCode.NOT_EXIST_TOKEN);
        }

        chain.doFilter(request, response);
    }

    public String getAppleId(HttpServletRequest request){
        return request.getHeader("apple-code");
    }

    public Authentication getAuthentication(String appleId) {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(appleId);
        return new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());
    }
}
