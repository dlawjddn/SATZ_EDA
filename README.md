### Spring Event를 공부한 뒤의 개인적인 감상

---

#### **Spring Event란?**

Spring Event는 애플리케이션 내의 컴포넌트 간에 Loose Coupling을 유지하면서 통신할 수 있도록 돕는 강력한 기능입니다. 이는 Observer Pattern을 기반으로 설계되었으며, 한 컴포넌트에서 발생한 이벤트를 다른 컴포넌트에서 처리할 수 있도록 합니다.

Spring에서 이벤트는 `ApplicationEventPublisher` 객체를 통해 publish되며, 해당 이벤트를 listen하고 있는 listener가 이를 subscribe하여 로직을 처리합니다.

---

#### **왜 Spring Event를 사용할까?**

프로젝트를 진행하다 보면, 특정 서비스에 **과도한 의존성**이 부여되는 경우를 자주 접할 수 있습니다. 심지어 단순히 로그 기록이나 이력 저장과 같은 일부 로직을 위해 전체 서비스에 큰 의존성을 추가해야 하는 경우도 있습니다. 이는 큰 낭비처럼 느껴졌지만, 적절한 해결책을 찾지 못했던 기억이 있습니다.

Spring Event는 이러한 문제를 해결하기 위한 훌륭한 솔루션입니다. 스프링 빈 간의 의존성을 분리하여, 특정 로직을 담당하는 컴포넌트에만 이벤트를 전달함으로써, 각 컴포넌트가 본인의 역할에만 집중할 수 있게 합니다. 

이것은 객체지향 설계 원칙 중 하나인 단일 책임 원칙(SRP)을 준수하는 데에도 큰 도움이 됩니다. 즉, 적절한 시점에 Event를 적용함으로써 각 컴포넌트가 책임을 분산하고 독립적으로 동작할 수 있는 구조를 설계할 수 있습니다.

---

#### **ApplicationEventPublisher 사용이 EDA인가?**

EDA(Event-Driven Architecture)를 구현한 적은 없지만, Spring의 `ApplicationEventPublisher` 사용이 곧 EDA를 의미한다고 보기는 어렵습니다.

제 개인적인 생각으로, "`ApplicationEventPublisher`를 사용하는 것이 EDA인가?"라는 질문에는 "아니오"라고 답할 것입니다.

1. **ApplicationEventPublisher는 기본적으로 '동기(Synchronous)'로 동작**
   - `ApplicationEventPublisher`를 사용하는 목적은 주로 **의존성 분리**이며, EDA를 사용하는 목적은 메시지 큐를 활용한 **성능 최적화**, **유연성**, **확장성**을 달성하는 데 있습니다.
   - EDA는 이벤트 발행자와 소비자가 비동기로 동작하는 것을 전제로 하며, 이는 메시지 브로커(Kafka, RabbitMQ 등)와 같은 시스템을 통해 이루어지는 것으로 알고 있습니다.
   - 반면, `ApplicationEventPublisher`의 기본 동작 방식은 동기적이기 때문에, 이를 단순히 사용한다고 해서 EDA의 적용이라고 보기는 어렵다고 생각합니다.

2. **ApplicationEventPublisher는 하나의 Application Context에서만 동작**
   - Spring Event는 기본적으로 하나의 Application Context 내에서만 작동합니다. 결국 하나의 프로세스 안에서 작동하며, 시스템 간의 통신이 필요한 분산 환경에서는 사용이 제한적입니다.
   - EDA는 일반적으로 메시지 브로커를 활용하여 서비스 간의 통신을 가능하게 하므로, `ApplicationEventPublisher`로 구현된 이벤트 처리 방식과는 근본적으로 다르다고 생각합니다.

---

#### **ApplicationEventPublisher에서의 비동기처리**

사실 이 부분에 대한 저의 대답이 위의 내용에서 전부 나왔습니다. 결론은 신경쓰지 않아도 된다는 점입니다.

원래 비동기로 처리할 부분은 비동기로 처리하고, 동기로 처리할 부분은 동기로 처리하면 됩니다. ApplicationEventPublisher은 의존성 분리를 지원하는 기능이지 EDA를 지원하는 기능이 아니기 때문입니다.


---

#### **결론**

Spring Event는 애플리케이션 내에서 **의존성을 분리**하고, **단일 책임 원칙**을 준수하며 **느슨한 결합**을 구현하는 데 유용한 도구입니다. 그러나 이는 애플리케이션 컨텍스트 내의 이벤트 처리에 국한되며, 메시지 브로커와 비동기 처리가 기본 전제로 요구되는 **EDA**와는 다른 목적과 사용 범위를 가지고 있습니다.

따라서, **`ApplicationEventPublisher`의 사용이 곧 EDA를 의미하지는 않는다**고 할 수 있습니다. 그러나 적절히 확장하거나, 메시지 브로커와 통합하여 EDA 스타일의 구조로 발전시킬 가능성은 충분합니다.

Spring Event는 작은 규모의 의존성 문제를 해결하는 데 적합하며, EDA는 시스템 간의 복잡한 통신과 확장성을 고려한 설계 방식이라는 점에서, 차이가 존재한다고 생각합니다.
