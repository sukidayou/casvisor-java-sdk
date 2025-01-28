package org.casbin.casvisor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.casbin.casvisor.config.Config;
import org.casbin.casvisor.entity.Record;
import org.casbin.casvisor.util.Map;
import org.casbin.casvisor.util.RecordOperations;
import org.casbin.casvisor.util.http.CasvisorResponse;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import java.io.IOException;

public class RecordService extends Service{

    public RecordService(Config config) {
        super(config);
    }

    public Record getRecord(String name) throws IOException {
        CasvisorResponse<Record, Object> response = doGet("get-record",
                Map.of("id", config.organizationName + "/" + name), new TypeReference<CasvisorResponse<Record, Object>>() {
                });
        return response.getData();
    }

    public List<Record> getRecords() throws IOException {
        CasvisorResponse<List<Record>, Object> response = doGet("get-records",
                Map.of("owner", config.organizationName), new TypeReference<CasvisorResponse<List<Record>, Object>>() {
                });
        return response.getData();
    }

    public java.util.Map<String, Object> getPaginationRecords(int p, int pageSize, @Nullable java.util.Map<String, String> queryMap) throws IOException {
        CasvisorResponse<Record[], Object> response = doGet("get-records",
                Map.mergeMap(Map.of("owner", config.organizationName,
                        "p", Integer.toString(p),
                        "pageSize", Integer.toString(pageSize)), queryMap), new TypeReference<CasvisorResponse<Record[], Object>>() {
                });
        return Map.of("casvisorRecords", response.getData(), "data2", response.getData2());
    }

    public CasvisorResponse<String, Object> addRecord(Record record) throws IOException {
        return modifyRecord(RecordOperations.ADD_Record,record,null);
    }

    public CasvisorResponse<String, Object> deleteRecord(Record record) throws IOException {
        return modifyRecord(RecordOperations.DELETE_Record,record,null);
    }

    public CasvisorResponse<String, Object> updateRecord(Record record) throws IOException {
        return modifyRecord(RecordOperations.UPDATE_Record,record,null);
    }

    private <T1, T2> CasvisorResponse modifyRecord(RecordOperations method, Record record, java.util.Map<String, String> queryMap) throws IOException {
        String id = record.owner + "/" + record.name;
        record.owner = config.organizationName;
        String payload = objectMapper.writeValueAsString(record);

        return doPost(method.getOperation(), Map.mergeMap(Map.of("id", id), queryMap), payload,
                new TypeReference<CasvisorResponse<T1, T2>>() {
                });
    }
}
