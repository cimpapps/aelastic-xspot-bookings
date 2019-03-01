package com.aelastic.xspot.bookings.repo;


import com.aelastic.xspot.bookings.models.dao.Table;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TableRepoTests {


    public static final String TABLES_COLLECTION = "tables";

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    TableRepository tableRepository;

    @Test
    public void givenSavedTable_whenQueryById_expectedTofind() {
        String tableId = UUID.randomUUID().toString();
        Table capacity = Table.builder().id(tableId).capacity(7).build();
        mongoTemplate.save(capacity, TABLES_COLLECTION);

        Optional<Table> byId = tableRepository.findById(tableId);

        Assert.assertEquals(byId.get(), capacity);

    }

    @Test
    public void givenSavedTable_whenUpdate_expectTheUpdateAfterQuery() {
        Table table = Table.builder()
                .placeId(UUID.randomUUID().toString())
                .capacity(10)
                .sector("outside").build();

        Table savedTable = mongoTemplate.save(table, TABLES_COLLECTION);

        savedTable.setSector("inside");

        tableRepository.save(savedTable);

        Optional<Table> result = tableRepository.findById(savedTable.getId());

        Assert.assertEquals(savedTable, result.get());

    }

    @Test
    public void saveTableInDifferentCollection_queryWithTableCollection_emptyResult() {

        Table table = Table.builder()
                .placeId(UUID.randomUUID().toString())
                .capacity(10)
                .sector("outside").build();

        Table otherTables = mongoTemplate.save(table, "otherTables");

        Optional<Table> result = tableRepository.findById(otherTables.getId());

        assertTrue(result.isEmpty());

    }


    @Test
    public void findTablesByByPlaceIdAndCapacity() {
        String placeId = UUID.randomUUID().toString();
        Table.TableBuilder builder = Table.builder()
                .placeId(placeId)
                .sector("outside");

        Table table1 = builder.capacity(3).build();
        table1 = tableRepository.save(table1);

        Table table2 = builder.capacity(5).build();
        table2 = tableRepository.save(table2);

        Table table3 = builder.capacity(7).build();
        table3 = tableRepository.save(table3);

        String placeId2 = UUID.randomUUID().toString();
        Table table4 = builder.capacity(10).placeId(placeId2).build();
        table4 = tableRepository.save(table4);

        List<Table> tables = tableRepository.findTableByPlaceIdAndCapacityGreaterThanEqual(placeId, 5);

        assertThat(tables, hasSize(2));
        assertThat(tables, both(contains(table2, table3))
                .and(not(contains(table1, table4))));

    }

}
