# Spring Batch load CSV file to H2 Database

Spring Batch Job that reads data from a CSV file, process that data and write it into a H2 database, job  metadata is stored in the Database.

1. http://localhost:9010/v1/load - Trigger point for Spring Batch
2. http://localhost:9010/h2-console - H2 Console for querying the in-memory tables.
