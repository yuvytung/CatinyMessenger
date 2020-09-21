package org.regitiny.catiny.messenger.web.rest.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-09-19T21:01:24.515+07:00[Asia/Bangkok]")

@Controller
@RequestMapping("${openapi.catiny-gateway.base-path:/api/rest}")
public class MessagesApiController implements MessagesApi {

    private final MessagesApiDelegate delegate;

    public MessagesApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) MessagesApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new MessagesApiDelegate() {});
    }

    @Override
    public MessagesApiDelegate getDelegate() {
        return delegate;
    }

}
