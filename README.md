# nifi-cron-groovy

The solution : 
Migrate enterprise database stored procedures and cron jobs to [Apache NiFi].

The key stack:
1. Apache NiFi - new cloud platform for data service
2. Groovy - Java script,  and package (groovy.sql) is more powerful than SQL


#### comments 
* Firstly, reference file (temp_table.sql), try it in MySQL(5.7+) or TiDB
* NiFi Templates file is : nifi-cron-groovy.xml
* groovy dir includes the all groovy script
* test the SP service , url: http://localhost:8088/
* ref project [fcp4cn/sps-boot-groovy], on TEMPORARY table. 

== See Also

The following guides may also be helpful:

* http://nifi.apache.org/index.html [Apache NiFi]
