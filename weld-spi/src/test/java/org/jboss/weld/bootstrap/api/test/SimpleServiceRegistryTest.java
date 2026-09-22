package org.jboss.weld.bootstrap.api.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.expectThrows;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.jboss.weld.bootstrap.api.Service;
import org.jboss.weld.bootstrap.api.helpers.SimpleServiceRegistry;
import org.testng.annotations.Test;

public class SimpleServiceRegistryTest {

    @Test
    public void iteratorVisitsEachServiceAndTerminates() {
        SimpleServiceRegistry registry = new SimpleServiceRegistry();
        Service first = () -> {
        };
        MockService second = new MockService() {
        };
        registry.add(Service.class, first);
        registry.add(MockService.class, second);

        Iterator<Service> iterator = registry.iterator();
        Set<Service> visited = new HashSet<>();
        assertTrue(iterator.hasNext());
        visited.add(iterator.next());
        assertTrue(iterator.hasNext());
        visited.add(iterator.next());
        assertFalse(iterator.hasNext());
        assertEquals(visited, Set.of(first, second));
        expectThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void iteratorSupportsRemoval() {
        SimpleServiceRegistry registry = new SimpleServiceRegistry();
        registry.add(Service.class, () -> {
        });
        Iterator<Service> iterator = registry.iterator();
        expectThrows(IllegalStateException.class, iterator::remove);
        iterator.next();
        iterator.remove();
        assertFalse(registry.contains(Service.class));
        assertFalse(iterator.hasNext());
        expectThrows(IllegalStateException.class, iterator::remove);
    }
}
