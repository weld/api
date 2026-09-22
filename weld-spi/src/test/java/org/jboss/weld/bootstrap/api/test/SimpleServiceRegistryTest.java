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

    @Test
    public void equalityComparesRegistriesAndPreservesTheObjectContract() {
        SimpleServiceRegistry first = new SimpleServiceRegistry();
        SimpleServiceRegistry second = new SimpleServiceRegistry();
        SimpleServiceRegistry third = new SimpleServiceRegistry();
        Service service = () -> {
        };
        first.add(Service.class, service);
        second.add(Service.class, service);
        third.add(Service.class, service);

        assertTrue(first.equals(first));
        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
        assertTrue(second.equals(third));
        assertTrue(first.equals(third));
        assertEquals(first.hashCode(), second.hashCode());
        assertFalse(first.equals(null));
        assertFalse(first.equals(java.util.Map.of(Service.class, service)));
        second.add(MockService.class, new MockService() {
        });
        assertFalse(first.equals(second));
        assertFalse(second.equals(first));
    }
}
