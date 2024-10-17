package com.example.sysark_lab2.resources;

import com.example.sysark_lab2.Category;
import com.example.sysark_lab2.Product;
import com.example.sysark_lab2.Warehouse;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Path("/products")
public class ProductResource {
    private static final Warehouse warehouse = new Warehouse();
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(@Valid Product product) {
        try {
            logger.info("Received product: {}", product);
            warehouse.addProduct(product);
            logger.info("Added product: {}", product);

            return Response.status(Response.Status.CREATED).entity("Product added successfully").build();
        } catch (IllegalArgumentException e) {
            logger.error("Error adding product", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts() {
        List<Product> products = warehouse.getAllProducts();
        logger.info("Fetching all products: {}", products);
        return warehouse.getAllProducts();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        Optional<Product> product = warehouse.getProductById(id);
        if (product.isPresent()) {
            logger.info("Fetching product: {}", product.get());
            return Response.ok().entity(product).build();
        } else {
            logger.warn("Product with id {} not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProductByCategory(@PathParam("category") Category category) {
        logger.info("Fetching all products by category: {}", category);
        return warehouse.getProductByCategory(category);
    }
}
