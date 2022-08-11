package com.example.monitorFinal;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQuery.TableDataListOption;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.TableId;
import com.google.cloud.bigquery.TableResult;

public class BrowseTable {



    public static void browseTable(String dataset, String table) {
        try {
            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests.
            BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

            // Identify the table itself
            TableId tableId = TableId.of(dataset, table);

            // Page over 100 records. If you don't need pagination, remove the pageSize parameter.
            TableResult result = bigquery.listTableData(tableId, TableDataListOption.pageSize(100));

            // Print the records
            result
                    .iterateAll()
                    .forEach(
                            row -> {
                                row.forEach(fieldValue -> System.out.print(fieldValue.toString() + ", "));
                                System.out.println();
                            });

            System.out.println("Query ran successfully");
        } catch (BigQueryException e) {
            System.out.println("Query failed to run \n" + e.toString());
        }
    }
}
// [END bigquery_browse_table]
