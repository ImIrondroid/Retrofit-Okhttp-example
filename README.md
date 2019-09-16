# Retrofit과 Okhttp 통신

by C.H.Choi

Retrofit은 REST API로, 서버와 클라이언트간 Http 통신을 위한 인터페이스 입니다. 클라이언트에서 서버로 어떠한 요청을 보내면 서버는 그 요청에 대한 응답을 클라이언트로 보내주게 되는데, 이 과정들을 쉽게 사용 할 수 있도록 도와주는 역할을 하는 것이 바로 Retrofit 입니다.

1. 인터넷 사용 권한 추가하기

  ```bash
  AndroidManifest.xml
  
  <uses-permission android:name="android.permission.INTERNET"></uses-permission>
  ```
  서버통신을 사용하기 위하여 인터넷 권한 추가를 꼭 추가 해줘야 함
  
  
2. Gradle에 라이브러리 추가하기

  ```bash
  dependencies {
    ...
    implementation 'com.squareup.retrofit2:retrofit:2.2.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
    implementation 'com.squareup.okhttp3:okhttp:3.8.1'
  }
  ```
  통신결과를 Gson형태로 변환하기 위하여 컨버터를 추가함


3. 모델클래스 만들기


4. 인터페이스 만들기  
  
  
 ### Retrofit의 편리한점
  
 Retrofit라이브러리는 기본적으로 OkHttp라이브러리를 포함하고 있습니다. Retrofit 은 파라메터, 쿼리, 헤더 등의 매핑작업, 결과 처리작업 등 반복되는 작업들을 편리 
 하게 처리할 수 있게끔 구현된 라이브러리입니다. <br/>  Retrofit을 사용하지 않고 okhttp만을 이용해서도 작업이 가능하나, Url 매핑, 파라메터 매핑등의 귀찮은 작업
 들이 많아지기 때문에, okhttp와 함께 사용합니다.
