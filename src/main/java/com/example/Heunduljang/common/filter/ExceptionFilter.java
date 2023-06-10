package com.example.Heunduljang.common.filter;

import com.example.Heunduljang.common.base.BaseResponse;
import com.example.Heunduljang.common.exception.BadRequestException;
import com.example.Heunduljang.common.exception.NotFoundException;
import com.example.Heunduljang.common.exception.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            log.info("appleId: {}", request.getHeader("apple-code"));

            filterChain.doFilter(request,response);
        }catch (UnauthorizedException e){
            ObjectMapper objectMapper = new ObjectMapper();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(response.getWriter(),
                    BaseResponse.onFailure(e.getErrorCode().getCode(), e.getResponseMessage(), request.getHeader("apple-code")));
        } catch (NotFoundException | BadRequestException e){

            ObjectMapper objectMapper = new ObjectMapper();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(response.getWriter(),
                    BaseResponse.onFailure(e.getErrorCode().getCode(), e.getResponseMessage(),null));
        }
    }
}
