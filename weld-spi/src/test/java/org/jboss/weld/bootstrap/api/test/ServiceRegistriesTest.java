package org.jboss.weld.bootstrap.api.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.bootstrap.api.ServiceRegistry;
import org.jboss.weld.bootstrap.api.helpers.ServiceRegistries;
import org.jboss.weld.bootstrap.api.helpers.SimpleServiceRegistry;
import org.testng.annotations.Test;

public class ServiceRegistriesTest {

    @Test
    public void unmodifiableRegistryRejectsAllRegistrationChanges() {
        ServiceRegistry backing = new SimpleServiceRegistry();
        Service original = () -> {
        };
        Service replacement = () -> {
        };
        backing.add(Service.class, original);
        ServiceRegistry view = ServiceRegistries.unmodifiableServiceRegistry(backing);

        expectThrows(UnsupportedOperationException.class, () -> view.add(Service.class, replacement));
        expectThrows(UnsupportedOperationException.class, () -> view.addIfAbsent(Service.class, replacement));
        expectThrows(UnsupportedOperationException.class, () -> view.addIfAbsent(MockService.class, new MockService() {
        }));
        expectThrows(UnsupportedOperationException.class,
                () -> view.addAll(List.of(Map.entry(Service.class, replacement))));
        expectThrows(UnsupportedOperationException.class, () -> view.entrySet().clear());
        expectThrows(UnsupportedOperationException.class,
                () -> view.entrySet().iterator().next().setValue(replacement));
        Iterator<Map.Entry<Class<? extends Service>, Service>> entries = view.entrySet().iterator();
        entries.next();
        expectThrows(UnsupportedOperationException.class, entries::remove);
        Iterator<Service> services = view.iterator();
        assertSame(services.next(), original);
        expectThrows(UnsupportedOperationException.class, services::remove);
        assertFalse(services.hasNext());
        assertSame(backing.get(Service.class), original);
        assertEquals(backing.entrySet().size(), 1);
    }

    @Test
    public void unmodifiableRegistryRemainsLiveAndDelegatesCleanup() {
        ServiceRegistry backing = new SimpleServiceRegistry();
        ServiceRegistry view = ServiceRegistries.unmodifiableServiceRegistry(backing);
        Set<Map.Entry<Class<? extends Service>, Service>> entries = view.entrySet();
        AtomicBoolean cleaned = new AtomicBoolean();
        Service service = () -> cleaned.set(true);
        backing.add(Service.class, service);
        assertSame(view.get(Service.class), service);
        assertEquals(entries.size(), 1);
        view.cleanup();
        assertTrue(cleaned.get());
    }
}
