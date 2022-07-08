# android-mail-19

# 메일 앱

## 사용 기술
- drawLayout
- BottomNavigation
- NavigationRail
- data binding
- LiveData
- ViewModel

## 화면
- 로그인 화면
<table>
		<tr>
			<td>
				<img
					src="https://user-images.githubusercontent.com/82700895/177913307-43a0d6fb-f7d8-4122-9a85-1e21a124a24e.png"
					width="230"
				/>
			</td>
			<td>
				<img
					src="https://user-images.githubusercontent.com/82700895/177913134-465e7d61-d41f-4611-8c70-ff222cdb2152.png"
					width="230"
				/>
			</td>
		</tr>
	</table>
  
  - 메일 탭
<table>
		<tr>
			<td>
				<img
					src="https://user-images.githubusercontent.com/82700895/177913550-79d9ba0d-2a2f-4da5-877b-7ce7b74e8017.png"
					width="230"
				/>
			</td>
      <td>
      <img
					src="https://user-images.githubusercontent.com/82700895/177913607-b3473cae-adb4-4aee-8789-d6c730e7694b.png"
					width="230"
				/>
			</td>
		</tr>
	</table>
  
- drawerLayout
<table>
		<tr>
			<td>
				<img
					src="https://user-images.githubusercontent.com/82700895/177913887-72a21d15-c0c4-4ff4-bee2-1769be6352d4.png"
					width="230"
				/>
			</td>
      <td>
      <img
					src="https://user-images.githubusercontent.com/82700895/177913927-458dca51-df6a-4f0a-8c23-c5b7f3cc49f6.png"
					width="230"
				/>
			</td>
		</tr>
	</table>
  - Setting 탭
<table>
		<tr>
			<td>
				<img
					src="https://user-images.githubusercontent.com/82700895/177914019-33b1a1f5-5f12-46c1-8e9e-705716953781.png"
					width="230"
				/>
			</td>
      <td>
      <img
					src="https://user-images.githubusercontent.com/82700895/177914070-15939302-ee8d-4e5d-b5ab-dedc115c55ff.png"
					width="230"
				/>
			</td>
		</tr>
	</table>
  
  - 태블릿
<table>
		<tr>
			<td>
				<img
					src="https://user-images.githubusercontent.com/82700895/177914177-715c1b7f-515d-4696-b366-893b0fe40e28.png"
					width="230"
				/>
			</td>
      <td>
      <img
					src="https://user-images.githubusercontent.com/82700895/177914219-6803db45-ffb0-43c7-aad3-0522fc4b78f8.png"
					width="230"
				/>
			</td>
		</tr>
	</table>

## 느낀점
- drawLayout, NavigationRail, LiveData observing 을 처음해봤는데, 미리 알았었다면 예전에 더 좋은 결과물을 만들어냈을 거 같다는 아쉬움이 있다.</br> (지금이라도 알아서 다행..)
- navigation 없이 화면전환을 하려니 코드량도 많아지고, 조금 불편하다... 
- 닉네임, 이메일 타당성 체크할 때 정규표현식을 사용했는데 좋은 방법인 거 같다.
