CREATE DATABASE FitTogether;
USE FitTogether;

-- 사용자 테이블 (users)
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
);

-- 게시글 테이블 (posts)
CREATE TABLE posts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(2083),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- 좋아요 테이블 (likes)
CREATE TABLE likes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    post_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE
);

-- 인덱스 생성 (효율적인 검색을 위해)
CREATE INDEX idx_user_id ON posts(user_id);
CREATE INDEX idx_post_id ON likes(post_id);



-- 사용자 테이블에 더미 데이터 추가 (users)
INSERT INTO users (username, email, password, created_at, updated_at) VALUES
('user1', 'user1@example.com', 'password1', NOW(), NOW()),
('user2', 'user2@example.com', 'password2', NOW(), NOW()),
('user3', 'user3@example.com', 'password3', NOW(), NOW());

-- 게시글 테이블에 더미 데이터 추가 (posts)
INSERT INTO posts (user_id, description, image_url, created_at, updated_at) VALUES
(1, '첫 번째 게시글입니다.', 'https://dcimg3.dcinside.co.kr/viewimage.php?id=26bcc22febd3&no=24b0d769e1d32ca73ce886fa1bd62531c28ee23c453cf874f616b8e80f09782cf0b327590fb144b4edfd410785082e40b72c1d613856e5953f9839855e6d', NOW(), NOW()),
(2, '두 번째 게시글입니다.', 'https://dcimg3.dcinside.co.kr/viewimage.php?id=26bcc22febd3&no=24b0d769e1d32ca73ce886fa1bd62531c28ee23c453cf874f616b8e80f09782cf0b327590fb144b4edfd410785082e40b72c1a6c3852ec9c349c39855e6d', NOW(), NOW()),
(3, '세 번째 게시글입니다.', 'https://dcimg3.dcinside.co.kr/viewimage.php?id=26bcc22febd3&no=24b0d769e1d32ca73ce886fa1bd62531c28ee23c453cf874f616b8e80f09782cf0b327590fb144b4edfd410785082e40b72c4c326f57e398349f39855e6d', NOW(), NOW());

-- 좋아요 테이블에 더미 데이터 추가 (likes)
INSERT INTO likes (user_id, post_id, created_at) VALUES
(1, 2, NOW()),
(2, 1, NOW()),
(3, 1, NOW());

INSERT INTO users (username, email, password, created_at, updated_at) VALUES
('master', 'ssafy@ssafy.com', '1234', NOW(), NOW());

SELECT * FROM users;