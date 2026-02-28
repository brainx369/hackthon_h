package com.fulfilment.application.monolith.location;

import com.fulfilment.application.monolith.warehouses.domain.models.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.wildfly.common.Assert.assertNotNull;

public class LocationGatewayTest {

    @Test
    public void testWhenResolveExistingLocationShouldReturn() {
        // given

            // given
            LocationGateway locationGateway = new LocationGateway();

            // when
            Location location = locationGateway.resolveByIdentifier("ZWOLLE-001");

            // then
            assertNotNull(location);
            assertEquals("ZWOLLE-001", location.identifier());
            assertEquals(1, location.maxNumberOfWarehouses());      // adjust if your record uses different names
            assertEquals(40, location.maxCapacity());
        }

        @Test
        public void testWhenResolveNonExistingLocationShouldReturnNull (){
            // given
            LocationGateway locationGateway = new LocationGateway();

            // when
            Location location = locationGateway.resolveByIdentifier("UNKNOWN-999");

            // then
            assertNull(location);
        }
    }
