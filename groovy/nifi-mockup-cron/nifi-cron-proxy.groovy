
//mockup CRON proxy

import org.apache.nifi.controller.ControllerService
import groovy.sql.Sql

//assume myConnectionPoolName property is linked to desired database connection pool  
def dbServiceName = myConnectionPoolName.value

def lookup = context.controllerServiceLookup
def dbcpServiceId = lookup.getControllerServiceIdentifiers(ControllerService).find { 
    cs -> lookup.getControllerServiceName(cs) == dbServiceName
}
def conn = lookup.getControllerService(dbcpServiceId)?.getConnection()
def sql = new Sql(conn)

//run SQL logic in groovy file, and get log
def binding = new Binding()
def engine = new GroovyScriptEngine('file:///Users/fcp/Downloads/2018NIFI/nifi-1.8.0/tidb/nifi-mockup-cron/test/')          
def greeter = engine.run('ReloadingTest.groovy', binding)                   
def sayHello = greeter.sayHello()
def greeterRestult = greeter.sayDB(sql) 

//return connection to pool!!
conn?.close()

//write log to flowFile
def flowFile = session.create()

flowFile.write("UTF-8"){wout -> 
  wout << sayHello<< '\n'
  wout << greeterRestult << '\n'

  wout << '<br> All OK' << '\n'
}

//set cron log filename
flowFile.'filename' = 'test-cron.log'

//ok
REL_SUCCESS << flowFile



