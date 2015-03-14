# garlic1ho
박소은님. 최강용 팀보드


#build
eclipe인 경우 : gradle eclipse
intelliJ 인 경우 : gradle idea

그리고 IDE 에서 불러온 뒤에, 이클립스같은 경우 
프로젝트에서 src/main/java , resources 와 테스트부분 폴더도 classpath 설정을 잡아줬어야 했다. 
이클립스의 경우 톰캣도 직접 환경설정 해줬었다.

인텔리J 도 마찬가지..
음.. 구글링중 sourceSets 가 자동으로 소스 classpath 잡아주는 것같던데, 고대로 했는데 잘 안됬다. 
크게 불편한 건 없으니(?) 일단 pass.;; 
