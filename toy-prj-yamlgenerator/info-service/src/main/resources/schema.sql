DROP TABLE URLS;

CREATE TABLE URLS (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      url VARCHAR(50),
                      search VARCHAR(50),
                      description VARCHAR(50),
                      crud VARCHAR(10),
                      type VARCHAR(15),
                      input_body VARCHAR(255)
);
ALTER TABLE URLS CONVERT TO CHARSET UTF8;

-- urlS table 내용
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/insertYaml', '/info/yaml_input', 'resource yml uri path를 body에 입력', 'create', 'POST', 'c:/file_path');

INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/insertFilter', '/info/filter/insert', '필터가 추가되어지는 Yaml 생성', 'create', 'POST', 'name,args,baseMessage,preLogger,postLogger');
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/insertFilter_pre', '/info/filter/insert', '가장 하단의 filter 삭제','create', 'POST', 'prefilter_name,name,args,baseMessage,preLogger,postLogger');
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/deleteFilter', '/info/filter/delete', '가장 하단의 filter 삭제','delete', 'POST', 'NO_INPUT');
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/deleteFilter_target', '/info/filter/delete', '특정 filter 설정 삭제','delete', 'POST', 'prefilter_name');
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/updateFilter', '/info/filter/update', '특정 filter 설정 수정', 'POST', 'update', 'prefilter_name,name,args,baseMessage,preLogger,postLogger');

INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/insertRouter', '/info/router/insert', '가장 하단 위치에 router 삽입', 'create', 'POST', 'id,uri,predicates');
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/insertRouter_pre', '/info/router/insert', '특정 위치에 라우터 삽입', 'create', 'POST', 'preNode_id,id,uri,predicates');
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/deleteRouter', '/info/router/delete', '마지막 router 설정 삭제','delete', 'POST', 'NO_INPUT');
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/deleteRouter_target', '/info/router/delete', '특정 router 설정 삭제', 'delete', 'POST', 'preNode_id');
INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/updateRouter', '/info/router/update', '특정 router 설정 수정', 'POST', 'update', 'preNode_id,id,uri,predicates');

INSERT INTO URLS(url, search, description, crud, type, input_body)
values('/createYaml', '/info/yaml_output', '완성된 yaml 다운로드로, body에 다운 받을 resouce name을 입력','create', 'POST', 'resource_name');


