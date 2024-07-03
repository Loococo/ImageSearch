# Image Seach
카카오 이미지 검색 API를 이용한 이미지 검색 앱

## 사용 패턴
- MVVM
- Clean Architecture

## 사용 라이브러리
- Compose
- Room
- Hilt
- Paging
- Coil
- Retrofit

### Cold Flow
- 플로우를 구독할 때마다 플로우의 시작 지점에서 데이터를 생성합니다.
- 데이터가 플로우 안에서 생깁니다.
- 네트워크 요청이나 데이터베이스 쿼리와 같은 작업에 사용됩니다.

### Hot Flow
- 여러 사용자가 동시에 접근하여 데이터를 공유할 수 있습니다.
- 데이터가 플로우 밖에서 생깁니다.
- 시간 업데이트나 센서 데이터와 같은 지속적인 이벤트 스트림에 사용됩니다.

### StateFlow
- 상태가 변경될 때마다 최신 상태를 구독자에게 자동으로 전달합니다.
- 초기값을 설정해야됩니다.

### SharedFlow
- 초기값을 설정하지 않아도 됩니다.
- 같은 값을 전달할 수 있습니다.

### Android ViewModel owner
- ViewModel을 소유하고 있는 객체, ViewModel의 생명주기와 관계가 있습니다.
- Owner가 파괴될 때 ViewModel도 함께 정리됩니다.
- 같은 owner를 사용하면 같은 ViewModel 인스턴스를 공유할 수 있습니다.

### PagingSource
- 데이터를 로드하는 데 사용
- 네트워크 호출이나 데이터베이스 쿼리를 사용하여 페이지화된 데이터를 전달합니다.
- load 함수가 있고, 비동기로 호출되며 LoadResult값으로 리턴합니다.

### getRefreshKey
- Paging 라이브러리에서 새로고침 과정에서 사용될 키를 생성하는 역할을 합니다.
- 마지막 로드된 데이터 상태를 나타내는 pagingState를 파라미터로 받습니다.
- 새로고침에 사용될 키를 반환합니다.

### load
- 파라미터로 LoadParams을 받고, key/loadSize을 포함하고 있습니다.
- LoadResult값으로 리턴되고, Page/Error 유형이 있습니다.
