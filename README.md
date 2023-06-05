# naver-sens-kotlin

네이버 클라우드 플랫폼 SENS(Simple & Easy Notification Service) 코틀린 라이브러리 입니다.

## Installation

*Step 1*. build.gradle 파일에 추가합니다.

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

*Step 2*. 의존성을 추가합니다.

```groovy
dependencies {
    implementation 'com.github.Handwoong:naver-sens-kotlin:v1.0.3'
}
```

## Usage

SENS에서 발급받은 accessKey, secretKey, serviceId를 가지고 `MessageSender` 인스턴스 생성 후 사용합니다.

```kotlin
val sender = MessageSender(accessKey, secretKey, serviceId)
```

- 알림톡 API

```
messageSender.sendAlimTalk(messageBody: MessageBody) // 알림톡 전송

messageSender.alimTalkSendRequestCheck(requestId: String) // 알림톡 발송 요청 조회

messageSender.alimTalkSendResultCheck(messageId: String) // 알림톡 발송 결과 조회

messageSender.alimTalkReserveStatusCheck(reserveId: String) // 예약 메시지 상태 조회

messageSender.alimTalkReserveCancel(reserveId: String) // 예약 메시지 취소

messageSender.alimTalkScheduleCancel(scheduleCode: String, messageId: String) // 스케줄 메시지 취소

messageSender.kakaoChannelCheck(pageSize: Int?, pageIndex: Int?) // 카카오톡 채널 조회

messageSender.alimTalkTemplateCheck(
    channelId: String,
    templateCode: String,
    templateName: String?,
    pageSize: Int?,
    pageIndex: Int?
) // 알림톡 템플릿 조회
```

현재 [네이버 SENS API](https://api.ncloud-docs.com/docs/ai-application-service-sens-alimtalkv2)의 알림톡 API 사용 가능합니다.


