package controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AsyncRequestHandler extends RequestHandler {
    @Override
    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
