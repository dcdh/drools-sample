package com.sample.memberof;

import com.sample.memberof.model.Applicant;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.drools.core.event.DebugAgendaEventListener;
import org.kie.api.runtime.KieRuntimeBuilder;
import org.kie.api.runtime.KieSession;

@ApplicationScoped
public class Application {

    @Inject
    KieRuntimeBuilder kieRuntimeBuilder;

    public void onStart(@Observes StartupEvent ev) {
        try (KieSession session = kieRuntimeBuilder.newKieSession()) {
            session.insert(new Applicant("John", true));
            session.insert(new Applicant("Paul", true));
            session.insert(new Applicant("George", true));

            int i = session.fireAllRules();
            Log.infov("Nb of rules fired {0}", i);
        }
    }
}
