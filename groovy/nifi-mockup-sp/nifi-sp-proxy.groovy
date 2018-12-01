//mockup sp proxy

//run SQL logic in groovy file
def binding = new Binding()
def engine = new GroovyScriptEngine('file:///Users/fcp/Downloads/2018NIFI/nifi-1.8.0/tidb/nifi-mockup-sp/test/')          
def greeter = engine.run('ReloadingTest.groovy', binding)                   
def sayHello = greeter.sayHello()
//assume SQL.mydb property is linked to desired database connection pool
def greeterRestult = greeter.sayDB(SQL.mydb) 

def flowFile = session.get()
flowFile.write("UTF-8"){wout -> 
  wout << sayHello << '\n'
  wout << greeterRestult << '\n'
  wout << '<br> SP OK' << '\n'
}

//set sp attribute
flowFile.'sp.name' = 'test.sp'

//ok
REL_SUCCESS << flowFile



