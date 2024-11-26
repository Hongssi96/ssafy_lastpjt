# ssafy_lastpjt
싸피 최종 프로젝트

---

# 프로젝트 개요

이 프로젝트는 Vue.js를 기반으로 한 게시물 관리 애플리케이션입니다. 사용자는 회원가입, 로그인, 게시물 작성, 삭제, 좋아요와 같은 기능을 사용할 수 있습니다. 프론트엔드와 백엔드 간의 통신은 Axios를 통해 이루어지며, Pinia를 사용해 상태 관리를 구현했습니다.

---

## 주요 기능

1. **회원 관리**
   - 회원가입: 사용자 이름, 이메일, 비밀번호를 입력하여 회원가입.
   - 로그인: 이메일과 비밀번호를 통해 사용자 인증.
   - 로그아웃: 세션을 초기화하고 로그인 페이지로 리다이렉트.
   - 회원 탈퇴: 계정을 삭제하고 관련 데이터를 초기화.

2. **게시물 관리**
   - 게시물 목록 보기: 모든 게시물 또는 특정 사용자의 게시물 조회.
   - 게시물 작성: 텍스트와 이미지를 업로드하여 새로운 게시물 작성.
   - 게시물 삭제: 특정 게시물 삭제.
   - 좋아요: 게시물에 좋아요를 추가.

3. **라우팅**
   - Vue Router를 활용한 페이지 간 이동:
     - `/` - 게시물 목록 페이지
     - `/create-post` - 게시물 작성 페이지
     - `/user` - 사용자 정보 페이지
     - `/login` - 로그인 페이지
     - `/signup` - 회원가입 페이지

4. **반응형 디자인**
   - 모바일 친화적인 반응형 스타일을 적용해 다양한 화면 크기에서 적절히 표시.

---

## 프로젝트 구조

```plaintext
src/
├── components/
│   ├── HeaderComponent.vue   # 헤더 컴포넌트
│   ├── PostItem.vue          # 개별 게시물 컴포넌트
├── views/
│   ├── LoginView.vue         # 로그인 페이지
│   ├── SignUpView.vue        # 회원가입 페이지
│   ├── PostListView.vue      # 게시물 목록 페이지
│   ├── UserView.vue          # 사용자 정보 페이지
│   ├── CreatePostComponent.vue # 게시물 작성 페이지
├── stores/
│   ├── users.js              # 사용자 관련 상태 관리 (Pinia)
│   ├── posts.js              # 게시물 관련 상태 관리 (Pinia)
├── App.vue                   # 메인 앱 컴포넌트
├── router/index.js           # 라우터 설정
└── main.js                   # 앱 초기화 파일
```

---

## 사용 기술 스택

- **Frontend**
  - Vue 3
  - Vue Router
  - Pinia (상태 관리)
  - Axios (HTTP 통신)

- **Backend**
  - REST API
  - (백엔드의 세부 구현은 이 문서에 포함되지 않았습니다.)

- **Styling**
  - Bootstrap
  - Scoped CSS
  - 반응형 디자인 지원

- **API**
  - imgbb (이미지를 url로 변환)
---

## 주요 코드 스니펫

### 1. 회원가입

```vue
<script setup>
import { ref } from 'vue';
import axios from 'axios';

const user = ref({
  username: '',
  email: '',
  password: ''
});
const confirmPassword = ref('');

async function signup() {
  if (user.value.password !== confirmPassword.value) {
    alert('비밀번호가 일치하지 않습니다.');
    return;
  }
  try {
    const response = await axios.post('http://localhost:8080/api/users', user.value);
    if (response.status === 201) {
      alert('회원가입이 완료되었습니다.');
    }
  } catch (error) {
    console.error('회원가입 실패:', error);
    alert('회원가입에 실패했습니다.');
  }
}
</script>
```

### 2. 게시물 작성

```vue
<script setup>
import { ref } from 'vue';
import axios from 'axios';

const post = ref({
  description: '',
  imageUrl: ''
});

async function createPost() {
  try {
    const response = await axios.post('http://localhost:8080/api/posts', post.value);
    if (response.status === 201) {
      alert('게시물이 성공적으로 작성되었습니다.');
    }
  } catch (error) {
    console.error('게시물 작성 실패:', error);
    alert('게시물 작성에 실패했습니다.');
  }
}
</script>
```

### 3. 게시물 삭제

```javascript
const deletePost = async (postId) => {
  try {
    await axios.delete(`http://localhost:8080/api/posts/${postId}`);
    alert('게시물이 삭제되었습니다.');
  } catch (error) {
    console.error('게시물 삭제 중 오류 발생:', error);
    alert('게시물 삭제에 실패했습니다.');
  }
};
```

---

## 설치 및 실행 방법

1. **프로젝트 클론**
   ```bash
   git clone https://github.com/Hongssi96/ssafy_lastpjt.git
   cd your-repo
   ```

2. **필요한 패키지 설치**
   ```bash
   npm install
   ```

3. **로컬 서버 실행**
   ```bash
   npm run dev
   ```

4. **API 서버 준비**
   - 백엔드 API 서버를 실행하고 `http://localhost:8080`에서 사용 가능하도록 설정.

---

## 향후 개선 사항

- **에러 핸들링 강화**: API 호출 실패에 대해 더욱 세분화된 에러 처리.
- **유저 프로필 사진 업로드**: 회원가입 또는 사용자 정보 페이지에서 이미지 업로드 기능 추가.
- **JWT 사용**: 인증 및 보안을 강화하기 위해 JWT 기반 인증 추가.

---
