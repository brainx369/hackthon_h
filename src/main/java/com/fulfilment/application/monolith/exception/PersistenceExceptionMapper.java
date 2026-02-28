package com.fulfilment.application.monolith.exception;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException exception) {

        // You can inspect cause to check if it's unique constraint
        if (exception.getCause() != null &&
            exception.getCause().getMessage().contains("Unique")) {

            return Response.status(Response.Status.CONFLICT)
                    .entity("Store already exists")
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Database error occurred")
                .build();
    }
}