# android-mail-19

# 메일 앱
## 공통 요구사항
- vector asset 사용
- 다크모드 on/off 고려 </br>
->  color-night 활용
- 정보 입력 화면과, 홈 화면으로 구성한다. </br>
-> 각각 로그인액티비티와, 메인액티비티로 구성

## 정보 입력 화면 요구사항
- 닉네임, 이메일 규칭을 정하고 이를 검증해야한다.
- 모두 규칙을 만족하는 경우 Next 버튼이 활성화된다.</br>
-> 정규식으로 닉네임, 이메일을 검증</br>
-> enable 속성을 이용해서 버튼 활성화/비활성화
- 기기를 회전시켜도 닉네임 / 이메일을 입력한 값은 유지되어야 한다. </br>
-> user의 정보를 저장하는 viewmodel 을 생성한 후, 데이터바인딩 </br>
-> EditText의 text의 변화를 감지할 때 마다, viewmodel의 livedata 업데이트
- Next 버튼 클릭 시, 사용자 정보 데이터를 다음 화면으로 전달한다. </br>
-> Intent에 bundle타입으로 담아 전달 

## 홈 화면 구성
- 홈화면으로 이동하면 로그인 된 것으로 간주한다.
- 백버튼을 클릭해도 '정보 입력 화면'으로 되돌아가지 않는다. </br>
-> overriding onBackPressed()  
- 하단 탭은 Mail, Setting 2개로 구성되며, 아이콘을 함께 보여준다.</br>
-> Bottom Navigation 사용
- 기본으로 선택된 탭은 Mail이다.
- 탭을 선택하면 화면을 이동하고, Mail, Setting 화면 내부에서 이동한 경로는 초기화된다.</br>
-> Mail탭 선택시, default 메일 리스트를 보여주도록 구현
- 메일 타입은 Primary, Social, Promotions 3가지 이다.
- Navigation Drawer 메뉴에 메뉴 타입을 추가하고, 클릭시 각 타입에 맞는 데이터를 출력 </br>
-> 화면이 회전되어도, 메일 리스트가 유지되어야 하므로 MailViewModel 선언 
-> MailViewModel 에 현재 메일 타입과, 리스트를 저장한다.
-> RecyclerView에서 메일 타입과 리스트를 observing 하여 각 타입에 맞게 뷰를 업데이트 시키도록 한다. 
- 수신한 메일목록은 발신인, 제목, 본문, 발신일 정보를 갖는다 </br>
-> 적절한 data class 선언, 더미데이터 생성
- 발신인 이름의 시작 문자가 영문인 경우, 발신인 아이콘에 첫번째 문자를 표기하고, 배경 색상을 랜덤으로 설정한다.</br>
-> 더미데이터를 생성할 때 시작문자를 체크하고, 그 background를 랜덤으로 설정하도록 한다.
- 발신인 이름의 시작 문자가 한글인 경우, 발신인 아이콘을 프로필 기본 아이콘으로 설정한다.
- Mail 화면에서 Social 또는 Promotions 으로 여러번 이동하더라도 백버튼을 클릭하면, 항상 Mail(Primary)로 이동한다.</br>
- 정보 입력 화면에서 입력받은 닉네임과 이메일을 보여준다.</br>
-> LoginActivity에서 Intent에 담아온 데이터로, userInfoViewModel 을 생성하고, SettingFragment에 데이터바인딩

## 태블릿 사이즈
- 가로길이가 600dp 이상인 경우, Navigation Rail로 변경이 구성된다.</br>
-> MainActivity 생성시 디바이스 width를 측정하고, 그에 맞게 bottomNavi 혹은 naviRail 을 init 시킨다.
- 기기를 가로/세로로 회전시켜도 선택된 탭은 유지되어야 한다.</br>
-> overriding onsavedInstance() 하여, 현재 탭 상태를 저장하고, onCreate() 시 저장된 상태에 맞게 화면을 set
