package org.casbin.casvisor;

import org.casbin.casvisor.entity.Record;
import org.casbin.casvisor.service.RecordService;
import org.casbin.casvisor.support.TestDefaultConfig;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class RecordTest {
    private final RecordService recordService = new RecordService(TestDefaultConfig.InitConfig());

    @Test
    public void testRecord() {
        String name = TestDefaultConfig.getRandomName("record");

        // Add a new object
        Record record = new Record(
                "casbin",
                name,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                "casbin",
                "120.85.97.21",
                "admin",
                "POST",
                "/api/test_request_uri",
                "test_action",
                "test_object",
                "en",
                "{\"status\":\"ok\", \"msg\":\"test_response\"}",
                true
        );
        assertDoesNotThrow(() -> recordService.addRecord(record));

        List<Record> records;
        try{
            records = recordService.getRecords();
        } catch (IOException e) {
            fail("Failed to get objects: " + e.getMessage());
            return;
        }

        boolean found = records.stream().anyMatch(item -> item.name.equals(name));
        assertTrue(found, "Added object not found in list");

        // Get the object
        Record retrieveRecord;
        try{
            retrieveRecord = recordService.getRecord(name);
        } catch (IOException e) {
            fail("Failed to get object: " + e.getMessage());
            return;
        }
        assertEquals(name, retrieveRecord.name, "Retrieved object does not match added object");

        // Update the object
        String updatedUser = "Updated Casvisor Website";
        retrieveRecord.user = updatedUser;
        assertDoesNotThrow(() -> recordService.updateRecord(retrieveRecord));

        // Validate the update
        Record updatedRecord;
        try{
            updatedRecord = recordService.getRecord(name);
        } catch (IOException e) {
            fail("Failed to get updated object: " + e.getMessage());
            return;
        }
        assertEquals(updatedUser, updatedRecord.user, "Failed to update object, User mismatch");

        // Delete the object
        assertDoesNotThrow(() -> recordService.deleteRecord(record));

        // Validate the deletion
        Record deletedRecord;
        try{
            deletedRecord = recordService.getRecord(name);
        } catch (IOException e) {
            fail("Failed to delete object: " + e.getMessage());
            return;
        }
        assertNull(deletedRecord, "Failed to delete object, it's still retrievable");
    }
}
