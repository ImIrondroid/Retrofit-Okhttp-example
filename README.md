# Retrofit과 Okhttp 통신

by C.H.Choi

Retrofit은 REST API로, 서버와 클라이언트간 Http 통신을 위한 인터페이스입니다.
클라이언트에서 서버로 어떠한 요청을 보내면 서버는 그 요청에 대한 응답을 클라이언트로 보내주게 되는데,
이 일련의 과정들을 쉽게 사용 할 수 있도록 도와주는 역할을 하는 것이 바로 Retrofit 입니다.

이 Retrofit, OkHttp통신 예제는 https://api.github.com/repos/square/retrofit/contributors 에서의 JSON 형식으로 된 정보들중에
login field의 값들을 모두 append하여 TextView에 나타내는 것 입니다. 

- 인터넷 권한 추가하기
  ```bash
  
  AndroidManifest.xml

  <uses-permission android:name="android.permission.INTERNET"></uses-permission>

  ```
  서버통신을 사용하기 위하여 인터넷 권한 추가를 꼭 해줘야 합니다.
  
 - Gradle에 라이브러리 추가하기

  ```bash
  
  dependencies {
  
    implementation 'com.squareup.retrofit2:retrofit:2.2.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
    implementation 'com.squareup.okhttp3:okhttp:3.8.1'
    
}
  ```
추가로 통신결과를 Gson형태로 변환하기 위하여 컨버터를 추가하였습니다.

- 모델클래스 만들기

  ```bash
  public class User {

    public final String login;
    public User(String login) {
        this.login = login;
    }
  }
  ```
  
- 인터페이스 만들기  
  
  ```bash
  public interface GetApi {

    @GET("/repos/{owner}/{repository}/contributors")

    // GetApi의 param을 넘기는 메소드
    Call<List<User>> sendParam(
            @Path("owner") String owner,
            @Path("repository") String repository
    );
  }
  
  ```
  당연히 @post, @put, @delete등을 지원합니다.
  
- 메인에서 Retrofit 구현하기
  
  ```bash
  public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private TextView textView;

    private final String BASE_URL = "https://api.github.com";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        
        executeRetrofit();
        //executeOkHttp();
    }

    //Retrofit이용하여 loginID 가져오기
    void executeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Api interface 생성
        GetApi getApi = retrofit.create(GetApi.class);
        
        // call에 인자들을 넣어 request를 만듬
        Call<List<User>> call = getApi.sendParam("square","retrofit");

        // request 수행
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
              // 성공
              
                List<User> users = response.body();

                for(User user : users) {
                    Log.d("Printed login", user.login);
                    textView.append("login ID : " +user.login+"\n");
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
              // 실패
            }
        });
    }

    //OkHttp이용하여 loginID가져오기
    void executeOkHttp() {

        new OkHttpAsyncTask(textView).execute("https://api.github.com/repos/square/retrofit/contributors");

    }
  }
  ```
- OkHttpAsynctask 구현하기
  ```bash
  public class OkHttpAsyncTask extends AsyncTask<String, Void, String> {

      OkHttpClient client = new OkHttpClient();
      TextView textView;

     // setTextView를 위해 Main에서 인자를 받기위함
     public OkHttpAsyncTask(TextView textView) {
         this.textView = textView;
     }
    
      @Override
     protected String doInBackground(String... strings) {
         String result = "";
         String strUrl = strings[0];  

          try { 
             // 요청하기
             Request request = new Request.Builder()
                     .url(strUrl)
                     .build();
             // 요청에대한 응답
             Response response = client.newCall(request).execute();

             JSONArray jsonArray = new JSONArray(response.body().string());

             for(int i = 0; i< jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String loginid = jsonObject.getString("login");
                result+="login ID : "+loginid+"\n";
            }

          } catch (IOException e) {
              e.printStackTrace();
          } catch (JSONException e) {
              e.printStackTrace();
         }
         return result;
      }

      @Override
      protected void onPostExecute(String s) {
          super.onPostExecute(s);
          if(s != null) {
             textView.setText(s);
          }
      }
  }
  ```
  
  
  - Retrofit의 편리한점
  
  Retrofit라이브러리는 기본적으로 OkHttp라이브러리를 포함하고 있습니다.
  Retrofit 은 파라메터, 쿼리, 헤더 등의 매핑작업, 결과 처리작업 등 반복되는 작업들을 편리하게 처리할 수 있게끔 구현된 라이브러리입니다.
  Retrofit을 사용하지 않고 okhttp만을 이용해서도 작업이 가능하나, Url 매핑, 파라메터 매핑등의 귀찮은 작업들이 많아지기 때문에, okhttp와 
  함께 사용합니다.
