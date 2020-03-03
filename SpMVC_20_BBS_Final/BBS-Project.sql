-- 여기는 BBS Final 화면입니다
SELECT * FROM tbl_bbs;


/*
    오라클의 계층 쿼리
    start with : 부모 결과값을 불러올 조건
    connect by prior 조건
        하위의 레코드를 모드 검색하면서 조건에 맞는 사항이 있으면
        해당하는 레코드를 나열하라
    order siblings by : connect by로 나열된 레코드들을 그룹별로 정렬하여 보여달라
*/
select * from tbl_bbs
start with b_p_id = 0
CONNECT by prior b_id = b_p_id
ORDER SIBLINGS by b_id DESC;


select * from tbl_bbs
start with b_p_id = 0
CONNECT by prior b_id = b_p_id
ORDER SIBLINGS by b_date_time;

/*
    sys_connect_By_path()
        start with, connect by 와 함꼐 사용되는 오라클의 시스템 함수
        connect by에 의해 나열된 레코드의 각 라인마다 현재 path 상태에 어떻게 되는지를 연산하는 함수
        
    부모레코드 부터 child 레코드 순으로 연산을 수행하면서
    부모제목 > child 제목 > ... 으로 연결하여 문자열을 생성하여 보여달라
*/
select b_id, b_p_id, b_subject, sys_connect_by_path(b_subject, '>') AS path
from tbl_bbs
start with b_p_id = 0
CONNECT by prior b_id = b_p_id
ORDER SIBLINGS by b_id;


/*
    Level : 오라클의 시스템 변수(칼럼)
        Connect by 에 의해 나열된 각 레코드를 분석하여
        현재 계층이 몇단계 레벨인지를 알려주는 변수 값
        
    Connect by prior의 조건식은 순서가 매우 중요하다
    기본적으로 prior ROOT = child 이다.
*/
select b_id, b_p_id, b_subject, level
from tbl_bbs
start with b_p_id = 0
CONNECT by prior b_id = b_p_id
ORDER SIBLINGS by b_id;


/*
    LPAD(문자열, 개수)
        '문자열'을 포함하여 개수만큼 길이의 문자열을 만들고, 문자열의 개수가 부족하면 왼쪽에서 빈칸을 채워넣어서
        문자열을 다시 정렬하여라
        
*/
select b_id, b_p_id, b_subject, LPAD('re:', (LEVEL-1)*3) || b_subject as 제목
from tbl_bbs
start with b_p_id = 0
CONNECT by prior b_id = b_p_id
ORDER SIBLINGS by b_id;

select * from tbl_comment;

delete from tbl_comment;

commit;