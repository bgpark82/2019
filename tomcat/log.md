- 아파치 톰캣이 내부적으로 사용하고 있는 것은 JULI 입니다. `java.util.logging` 를 사용하기 위한 프레임워크라고 생각하시면 됩니다.  톰캣이나 어플리케이션의 로깅을 독립적으로 유지할 수 있게 만들어 줍니다.

톰캣 위에서 돌아가는 어플리케이션은 다음과 같은 로깅 서비스를 사용할 수 있습니다.

- 아무 로깅 프레임워크
- `java.util.logging` 과 같은 시스템 로그 api를 사용가능
- 서블릿에서 제공하는 `javax.servlet.ServletContext.log(...)` 사용가능

로깅 프레임워크는 독립적으로 어플리케이션마다 사용 가능 합니다.

유일한 예외는 `java.util.logging` 인데, 이는 시스템 클래스 로더에 의해 로딩되어서 웹 어플리케이션 전역에서 공유됩니다.

### Java - java.util.logging

- JULI는 `java.util.logging` 기반의 톰캣만의 log api입니다.
- 특이한 점은 logManager 라는게 있는데 이 친구가 톰캣에서 도는 서로 다른 어플리케이션을 구분한다는 것입니다.
- 또한 어플리케이션이 메모리에 unload 되는 것도 감지하여 메모리 누수를 막기도 합니다.
- `java.util.logging` 을 실행하는 것은 톰캣의 `[startup.sh](http://startup.sh)` 를 실행하면 됩니다.

## Servlet logging API

- `javax.servlet.ServletContext.log()` 는 로그 메세지를 쓰는데 사용됩니다.
- 내부 톰캣 로깅에 의해 다음과 같이 관리됩니다.

    org.apache.catalina.core.ContainerBase.[${engine}].[${host}].[${context}]

- 로깅은 톰캣의 logging configuration에 의해 실행됩니다.
- 외부에서는 오버라이드 될 수 없으며, `java.util.logging` 에 선행되어 실행됩니다.
- 그리고 로그 레벨을 컨트롤 하는 등의 옵션을 주지도 않습니다.

## Console

- 톰캣을 유닉스에서 실행하면, 콘솔에서 보이는 로그 메세지들을 `catalina.log` 파일로 보내집니다.
- 즉, 우리가 이클립스나 인텔리제이에서 보여지는 로그메세지들은 모두 `catalina.out` 파일에 저장됩니다.
- 톰캣 베이스 파일의 bin 폴더 속의 `[startup.sh](http://startup.sh)` 나 `[catalina.sh](http://catalina.sh)` 에서 이 파일을 설정할 수 있습니다.
- `system.err/out`에서 적힌 내용은 모두 이 파일로 들어갑니다.
- 앞서 말한대로 유닉스에만 해당하므로 윈도우는 다른 이름으로 콘솔내용이 저장됩니다. (아마 catalina.cat으로 기억합니다.)

## java.util.logging 사용

- 사실 JDK에서 제공하는`java.util.logging` 을 기본으로 사용하기에는 조금 부족함이 있습니다.
- 가상머신에 대한 로깅만 존재하고 어플리케이션 별 로깅이 제공되지 않기 때문입니다.
- 그래서 톰캣이 LogManager를 컨테이너 단위(어플리케이션 단위) 로깅을 가능하게 하는 JULI라는 녀석을 제공해 줍니다.
- JUIL는 JDK에서 제공하는 `java.util.logging` 과 같은 설정 메커니즘을 제공해줍니다. (설정 파일로 가능합니다)
- JULI는 기본적으로 classloader 별 설정과`java.util.logging` 설정이 가능합니다.
- global
    - `${catalina.base}/conf/logging.properties` 에서 설정
    - startup.sh에 설정된`java.util.logging.conf.file` 시스템 설정 파일에 구체화 되어있습니다.
    - 설정 되지 않거나 readable로 되어있지 않으면 jre의 `${java.home}/lib/logging.properties`
- 웹 어플리케이션
    - `WEB-INF/classes/logging.properties` 에서 설정 가능합니다.

Handler의 로깅 Level

- SERVERE
- WARNING
- INFO
CONFIG
- FINE
- FINER
- FINEST
- ALL

JULI와 `java.util.logging` 의 차이

- handler 이름으로 prefix가 사용됨

`org.apache.juli.FileHandler` 는 로그의 버퍼를 제공해줍니다.

- 기본설정으로 버퍼를 지원하지 않습니다.
- handler의 `buffersize` 프로퍼티를 사용합니다.
- `0` 은 시스템 기본 버퍼링으로 사용됩니다. (보통 8k 버퍼가 사용됨)
- `<0` 각 로그가 적히면 강제로 버퍼를 내립니다.
- `<0` BufferedOuputStream 사용

## [logging.property](http://logging.property) file


``` 
{CATALINA_BASE}/conf

    handlers = 1catalina.org.apache.juli.FileHandler, \
               2localhost.org.apache.juli.FileHandler, \
               3manager.org.apache.juli.FileHandler, \
               java.util.logging.ConsoleHandler
    
    .handlers = 1catalina.org.apache.juli.FileHandler, java.util.logging.ConsoleHandler
    
    ############################################################
    # Handler specific properties.
    # Describes specific configuration info for Handlers.
    ############################################################
    
    1catalina.org.apache.juli.FileHandler.level = FINE
    1catalina.org.apache.juli.FileHandler.directory = ${catalina.base}/logs
    1catalina.org.apache.juli.FileHandler.prefix = catalina.
    
    2localhost.org.apache.juli.FileHandler.level = FINE
    2localhost.org.apache.juli.FileHandler.directory = ${catalina.base}/logs
    2localhost.org.apache.juli.FileHandler.prefix = localhost.
    
    3manager.org.apache.juli.FileHandler.level = FINE
    3manager.org.apache.juli.FileHandler.directory = ${catalina.base}/logs
    3manager.org.apache.juli.FileHandler.prefix = manager.
    3manager.org.apache.juli.FileHandler.bufferSize = 16384
    
    java.util.logging.ConsoleHandler.level = FINE
    java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
    
    
    ############################################################
    # Facility specific properties.
    # Provides extra control for each logger.
    ############################################################
    
    org.apache.catalina.core.ContainerBase.[Catalina].[localhost].level = INFO
    org.apache.catalina.core.ContainerBase.[Catalina].[localhost].handlers = \
       2localhost.org.apache.juli.FileHandler
    
    org.apache.catalina.core.ContainerBase.[Catalina].[localhost].[/manager].level = INFO
    org.apache.catalina.core.ContainerBase.[Catalina].[localhost].[/manager].handlers = \
       3manager.org.apache.juli.FileHandler
    
    # For example, set the org.apache.catalina.util.LifecycleBase logger to log
    # each component that extends LifecycleBase changing state:
    #org.apache.catalina.util.LifecycleBase.level = FINE
```

## WEB-INF/classes
```
    handlers = org.apache.juli.FileHandler, java.util.logging.ConsoleHandler
    
    ############################################################
    # Handler specific properties.
    # Describes specific configuration info for Handlers.
    ############################################################
    
    org.apache.juli.FileHandler.level = FINE
    org.apache.juli.FileHandler.directory = ${catalina.base}/logs
    org.apache.juli.FileHandler.prefix = ${classloader.webappName}.
    
    java.util.logging.ConsoleHandler.level = FINE
    java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
```