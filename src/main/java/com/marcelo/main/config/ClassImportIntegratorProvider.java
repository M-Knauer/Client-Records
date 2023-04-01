package com.marcelo.main.config;

import java.util.List;

import org.hibernate.integrator.spi.Integrator;
import org.hibernate.jpa.boot.spi.IntegratorProvider;

import com.marcelo.main.dto.ClientMinDTO;

import io.hypersistence.utils.hibernate.type.util.ClassImportIntegrator;

public class ClassImportIntegratorProvider implements IntegratorProvider {
    @Override
    public List<Integrator> getIntegrators() {
        return List.of(new ClassImportIntegrator(List.of(ClientMinDTO.class)));
    }
}