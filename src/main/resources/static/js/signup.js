// 모달 열기/닫기 함수
function openModal(id) {
    document.getElementById(id).style.display = 'flex';
}
function closeModal(id) {
    document.getElementById(id).style.display = 'none';
}

// // DB에서 어떻게 가져오는지 몰라서 일단 이렇게 해놨어욥..
// // 임시 "데이터베이스": 기존에 등록된 아이디 목록
// const existingUsernames = ['user1', 'testuser', 'abc123', 'member'];
// let isUsernameChecked = false;

// //중복 확인 버튼 클릭
// document.getElementById('checkBtn').addEventListener('click', function() {
//     const usernameInput = document.getElementById('username').value;

//     //아이디가 비어있을 경우
//     if (!usernameInput) {
//         alert('아이디를 입력해주세요.');
//         return;
//     }
//     //데이터베이스에 아이디 존재 확인
//     if (existingUsernames.includes(usernameInput)) {
//         alert('중복된 아이디 입니다.'); // 중복된 경우
//         isUsernameChecked = false;
//     } else {
//         alert('사용할 수 있는 아이디 입니다.'); // 사용 가능한 경우
//         isUsernameChecked = true;
//     }
// });

// //가입하기 버튼 클릭
// document.querySelector('form').addEventListener('submit', function(e) {
//     e.preventDefault(); //폼의 기본 제출 동작 방지

//     //비밀번호, 비밀번호 확인
//     const pwInputs = document.querySelectorAll('input[type=password]');
//     const password = pwInputs[0].value;
//     const passwordConfirm = pwInputs[1].value;

//     //중복확인이 완료되지 않은 경우
//     if (!isUsernameChecked) {
//         alert('아이디 중복확인이 필요합니다.');
//         return;
//     }
//     //비밀번호확인이 완료되지 않은 경우
//     else if (password !== passwordConfirm) {
//         alert('비밀번호 확인을 해주세요.');
//         return;
//     }
//     //전부 완료된 경우
//     else {
//         alert('회원가입이 성공적으로 완료되었습니다.');
//         window.location.href = '@{/login}'; //로그인 페이지로 이동
//     }
// })


document.addEventListener('DOMContentLoaded', () => {
  const checkBtn = document.getElementById('checkBtn');
  const usernameInput = document.getElementById('username');
  const feedbackEl = document.getElementById('username-feedback');

  checkBtn.addEventListener('click', async () => {
    const username = usernameInput.value.trim();
    // 입력 체크
    if (!username) {
      feedbackEl.textContent = '아이디를 입력해주세요.';
      feedbackEl.className = 'feedback error';
      return;
    }
    try {
      const url = /*[[@{/api/users/check-username}]]*/ '/api/auth/check-username';
      const res = await fetch(`${url}?username=${encodeURIComponent(username)}`, {
        method: 'POST'
      });
      if (!res.ok) throw new Error(`HTTP ${res.status}`);
      const data = await res.json();
      if (data.exists) {
        feedbackEl.textContent = '중복된 아이디입니다.';
        feedbackEl.className = 'feedback error';
      } else {
        feedbackEl.textContent = '사용할 수 있는 아이디입니다.';
        feedbackEl.className = 'feedback success';
      }
    } catch (err) {
      console.error(err);
      feedbackEl.textContent = '서버와 통신 중 오류가 발생했습니다.';
      feedbackEl.className = 'feedback error';
    }
  });
});