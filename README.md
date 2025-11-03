# 🎰 로또 - 구현 기능 목록

## 1️⃣ 입력 처리 기능
- [x] 사용자로부터 **로또 구입 금액**을 1000원 단위로 입력받고, **당첨 번호(6개)** 는 쉼표(`,`)를 기준으로, **보너스 번호(1개)** 를 입력받는다.
- [x] 구입 금액이 1000원으로 나누어 떨어지지 않을 경우 **예외를 발생시킨다.**
- [x] 당첨 번호가 6개가 아니거나, 쉼표 구분이 잘못되었거나, 숫자가 범위를 벗어날 경우 **예외를 발생시킨다.**
- [x] 또한 당첨 번호가 **중복될 경우 예외를 발생시킨다.**
- [x] 보너스 번호 또한 범위를 벗어나거나 잘못 입력했을 경우 **예외를 발생시킨다.**
- [x] 입력 예외가 발생할 경우 프로그램을 종료하지 않고, `[ERROR]`로 시작하는 에러 메시지를 출력한 후 **그 부분부터 다시 입력받는다.**

---

## 2️⃣ 로또 객체 생성 및 상태 관리
- [x] `Lotto` 클래스를 생성하여 로또 번호 6개를 관리하고 유효성을 검증한다.
- [x] 생성자에서 로또 번호의 **null 여부, 개수, 범위(1-45), 중복** 등을 검증한다.
- [x] `LottoService`에서 `Randoms.pickUniqueNumbersInRange(1, 45, 6)`를 활용하여 추첨 번호를 무작위로 생성한다.
- [x] 생성된 로또 번호는 오름차순으로 정렬하여 관리한다.
- [x] 사용자가 입력한 금액에 맞게 로또 티켓을 발행하고 `List<Lotto>` 형태로 반환한다.
- [x] 로또 객체는 불변성을 유지하도록 설계되었다. (`final` 필드 사용)

---

## 3️⃣ 당첨 통계 관리
- [x] **Enum**으로 각 당첨 등수를 `StatisticsType`으로 정의했다.
    - `MATCH_3`: 3개 일치 (5,000원)
    - `MATCH_4`: 4개 일치 (50,000원)
    - `MATCH_5`: 5개 일치 (1,500,000원)
    - `MATCH_5_BONUS`: 5개 일치 + 보너스 볼 (30,000,000원)
    - `MATCH_6`: 6개 일치 (2,000,000,000원)
- [x] 각 등수별 일치 개수, 보너스 번호 일치 여부, 당첨 금액을 `StatisticsType` Enum에서 관리한다.
- [x] `WinningStatistics` 클래스에서 각 당첨 타입별 당첨 개수를 관리한다.
- [x] `of` 정적 메소드를 통해 일치하는 번호 개수와 보너스 번호 일치 여부에 따라 적절한 `StatisticsType`을 반환한다.
- [x] `LottoService`의 `compareLottoTicket` 메소드에서 구매한 로또와 당첨 번호를 비교하여 당첨 통계를 생성한다.

---

## 4️⃣ 수익률 계산
- [x] `LottoService`의 `calculateProfitRate` 메소드에서 구매 금액과 당첨 통계를 기반으로 수익률을 계산한다.
- [x] 수익률은 (총 당첨금액 / 구매금액) * 100으로 계산되며, 소수점 둘째자리까지 반올림하여 표시한다.
- [x] 당첨 통계 목록을 순회하며 각 등수별 당첨 금액 * 당첨 개수를 합산하여 총 당첨금액을 계산한다.
- [x] 구매 금액이 0인 경우나 당첨 통계가 null인 경우 적절한 예외를 발생시킨다.

---

## 5️⃣ 실행 결과 출력
- [x] 발행된 **로또 수량 및 각 로또 번호**를 출력한다.
    - 예시:
      ```
      8개를 구매했습니다.
      [8, 21, 23, 41, 42, 43]
      [3, 5, 11, 16, 32, 38]
      [7, 11, 16, 35, 36, 44]
      ...
      ```
- [x] 당첨 결과를 아래와 같은 형식으로 출력한다.
    - 예시:
      ```
      당첨 통계
      3개 일치 (5,000원) - 1개
      4개 일치 (50,000원) - 0개
      5개 일치 (1,500,000원) - 0개
      5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
      6개 일치 (2,000,000,000원) - 0개
      총 수익률은 62.5%입니다.
      ```

---

## 6️⃣ 코드 규칙 및 품질
- [x] **들여쓰기(depth)** 는 최대 2단계까지만 허용했다.
- [x] **삼항 연산자**를 사용하지 않았다.
- [x] 메서드는 **한 가지 역할만 수행**하도록 최대한 작게 분리했다.
- [x] **Java Code Convention**을 준수했다.
- [x] `else` 예약어나 `switch/case` 문을 사용하지 않았다.
- [x] **단위 테스트**를 작성했다.
- [x] **Java Enum**을 적용하여 프로그램을 구현했다.

---

## 7️⃣ 예외 처리
- [x] 각 도메인별 예외를 `global/error` 패키지에서 중앙 관리한다.
- [x] `ErrorCode` Enum으로 모든 에러 메시지를 관리한다.
- [x] `ExceptionFactory`를 통해 일관된 방식으로 예외를 생성한다.
- [x] 커스텀 예외 클래스를 만들어 예외의 타입을 명확히 구분한다.
    - `LottoIllegalArgumentException`
    - `LottoIllegalStateException`
    - `LottoIndexOutOfBoundsException`
    - `LottoNullPointerException`
    - `LottoNumberFormatException`

---

## 8️⃣ 테스트 코드 (JUnit5 + AssertJ)
- [x] 구입 금액이 1000원 단위가 아닐 때 예외 발생 테스트
- [x] 로또 번호가 6개가 아닐 때 예외 발생 테스트
- [x] 로또 번호가 중복되었거나 범위를 벗어났을 때 예외 발생 테스트
- [x] 발행된 로또 개수가 구입 금액 / 1000 과 일치하는지 테스트
- [x] 당첨 번호와의 일치 개수에 따라 올바른 등수(1~5등)가 계산되는지 테스트
- [x] 수익률 계산이 올바른지 테스트
- [x] 모든 테스트는 Given-When-Then 패턴을 따른다.

---

## 9️⃣ 패키지 구조
프로젝트는 다음과 같은 패키지 구조로 구현되었습니다:

```
lotto
├── domain
│   ├── application
│   │   └── service
│   │       ├── LottoService.java (로또 서비스 인터페이스)
│   │       └── impl
│   │           └── LottoServiceImpl.java (로또 서비스 구현체)
│   ├── entity
│   │   ├── lotto
│   │   │   └── Lotto.java (로또 번호 관리 엔티티)
│   │   └── statistics
│   │       ├── StatisticsType.java (당첨 등수 Enum)
│   │       └── WinningStatistics.java (당첨 통계 엔티티)
│   └── presentation
│       ├── controller
│       │   └── LottoController.java (사용자 입력과 출력 제어)
│       └── view
│           ├── InputView.java (사용자 입력 담당)
│           ├── OutputView.java (결과 출력 담당)
│           ├── parser
│           │   └── LottoInputParser.java (입력값 파싱)
│           └── validator
│               └── LottoValidator.java (입력값 검증)
├── global
│   ├── config
│   │   ├── ApplicationFactory.java (의존성 주입)
│   │   └── ApplicationRunner.java (애플리케이션 실행)
│   ├── error
│   │   ├── ErrorCode.java (에러 코드 Enum)
│   │   ├── ExceptionFactory.java (예외 객체 생성)
│   │   └── exception
│   │       ├── LottoIllegalArgumentException.java
│   │       ├── LottoIllegalStateException.java
│   │       ├── LottoIndexOutOfBoundsException.java
│   │       ├── LottoNullPointerException.java
│   │       └── LottoNumberFormatException.java
│   └── message
│       └── MessageCode.java (메시지 코드 Enum)
└── Application.java (메인 클래스)
```

---

# 🧾 Commit Convention (AngularJS Style)

| Type | Description |
|------|--------------|
| **feat** | 새로운 기능 추가 |
| **fix** | 버그 수정 |
| **docs** | 문서 수정 (README 등) |
| **style** | 코드 포맷 변경 (세미콜론, 공백 등 — 기능 영향 없음) |
| **refactor** | 코드 리팩토링 (기능 변경 없이 구조 개선) |
| **test** | 테스트 코드 추가 또는 수정 |
| **chore** | 빌드, 설정, 기타 유지보수 작업 |