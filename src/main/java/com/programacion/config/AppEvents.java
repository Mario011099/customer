package com.programacion.config;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AppEvents {

    private String name="mp01";
    private String id;

    @ConfigProperty(name="quarkus.http.port", defaultValue="8080")
    private Integer port;

    @ConfigProperty(name="consul.ip", defaultValue="127.0.0.1")
    private String consulIp;

    @PostConstruct
    public void inicializar(){
        id = name + "-" + UUID.randomUUID().toString();
    }

    //Equivalente en CDI
    // public void init (@Observes @Initialized(ApplicationScoped.class)Object ev)
//    public void init(@Observes StartupEvent ev) throws Exception {
//        String ip = InetAddress.getLocalHost().getHostAddress();
//
//        System.out.println("**********************IP    :"+ip);
//        System.out.println("**********************PUERTO:"+port);
//        System.out.println("**********************CONSUL"+consulIp);
//
//        //registrar el servicio
//        Consul consulClient = Consul.builder()
//                .withHostAndPort(HostAndPort.fromParts(consulIp,8500))
//                .build();
//
//
//        String urlChequeo = String.format("http://%s:%d/q/health/live",ip,port);
//
//        String rule = String.format("traefik.http.routers.mp01.rule=PathPrefix(`/mp01`)");
//        String mid = String.format("traefik.http.routers.mp01.middlewares=mid01");
//        String mid1 = String.format("traefik.http.middlewares.mid01.stripprefix.prefixes=/mp01");
//        List<String> tags = Arrays.asList(rule,mid, mid1);
//
//        Registration service = ImmutableRegistration.builder()
//                .id(id)                 //instancia
//                .name(name)             //nombre
//                .address(ip)   //IP
//                .port(port)             //puerto
//                .putMeta("ip", ip)
//                .putMeta("puerto", port.toString())
//                .check(
//                        Registration.RegCheck.http(urlChequeo, 10, 3)
//                )
//                .tags(tags)
//                .build();
//        consulClient.agentClient().register(service);
//
//
//    }
//
//    public void destroy(@Observes ShutdownEvent ev){
//        System.out.println("**************terminando");
//        Consul consulClient = Consul.builder().build();
//        //eliminar el registro
//        consulClient.agentClient().deregister(id);
//
//
//    }
}
