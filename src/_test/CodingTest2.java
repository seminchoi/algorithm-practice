package _test;

import java.util.LinkedList;
import java.util.List;

public class CodingTest2 {
    class Solution {
        class StudentInfo {
            int id;
            int record;

            public StudentInfo(int id, int record) {
                this.id = id;
                this.record = record;
            }
        }
        public int solution(int n, int[] student, int[] point) {
            //학생들의 점수, 번호를 등수대로 연결리스트에 저장
            //우반의 꼴등 점수, 번호와 열반의 1등 점수, 번호를 변수에 기록
            //점수가 변동되었을 때 answer 증가
            //단점 : 탐색에 O(n) 복잡도, 장점 : 삽입,제거는 O(1)
            int answer = 0;

            List<StudentInfo> studentInfoList = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                studentInfoList.add(new StudentInfo(i+1,0));
            }


            for (int i = 0; i < student.length; i++) {
                int curIdx = 0;
                for (int j = 0; j < n; j++) {
                    if(studentInfoList.get(j).id == student[i])
                        curIdx = j;
                }

                StudentInfo curStudent = studentInfoList.get(curIdx);
                studentInfoList.remove(curIdx);
                curStudent.record += point[i];

                for (int j = 0; j < n; j++) {

                    if(j ==  n-1){
                        studentInfoList.add(curStudent);
                        if((curIdx < n/2 && j >= n/2) || (curIdx >= n/2 && j < n/2))
                            answer++;
                        break;
                    }

                    StudentInfo cmpStudent = studentInfoList.get(j);

                    if(cmpStudent.record > curStudent.record) {
                        continue;
                    }

                    else if(cmpStudent.record == curStudent.record
                            && cmpStudent.id < curStudent.record){
                            continue;
                    }
                    else{
                        studentInfoList.add(j,curStudent);
                        if((curIdx < n/2 && j >= n/2) || (curIdx >= n/2 && j < n/2))
                            answer++;
                        break;
                    }

                }
            }

            return answer;
        }
    }
}
