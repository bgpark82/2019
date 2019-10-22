# Comparator를 람다로 바꾸어 정렬하기

## Comparator
먼저 Comaparator에 대해 알아보겠습니다.
* interface
  * compare(Object obj1, Object obj2)
  * equals(Object element)
* 유저가 만든 클래스의 오브젝트를 정렬
* 2개의 클래스의 오브젝트를 비교가능

옛날 방식
```
class Sortbyname implements Comparator<Student> 
{ 
    public int compare(Student a, Student b) 
    { 
        return a.name.compareTo(b.name); 
    } 
} 

Collections.sort(ar, new Sortbyroll()); 
```
* comparator 인터페이스를 상속받은 클래스 내에 comparator 내의 두개의 메소드 (compare, equal)을 호출하여 사용
* Collections.sort에서 사용할 때 comparator를 상속받은 클래스의 인스턴스를 사용하여 정렬


* 두개의 요소를 비교 
* compare 메소드는 1,0,-1을 리턴

Collector 사용시
```
Collections.sort(list, comp);
```
* Collections.sort는 compare 메소드를 호출 

덜 옛날 방식
```
Comparator<T> comp = new Comparator<T> () {
    public int compare(T m1, T m2){
        return m1.compareTo(m2)
    }
}
Collections.sort(list, comp);
```

최근 방식
```
Collections.sort(list, (m1, m2)-> m1.compareTo(m2))
```
순서를 살펴보자면
```
Comparator<T> comp = new Comparator<T> () {
    public int compare(T m1, T m2){
        return m1.compareTo(m2)
    }
}
Collections.sort(list, comp);
```
덜 옛날 방식에서 comp는 new Comparator() {...}와 같으므로 내부로 넣을 수 있습니다.

```
Collections.sort(list, new Comparator<T>() {
    public int compare(T m1, T m2){
        return m1.compareTo(m2)
    }
})
```
그리고 compare를 람다 함수로 바꾸겠습니다. compare은 익명의 함수가 되므로 무시가 가능합니다. 또한 람다에서는 파라미터의 타입은 미리 결정되므로 생략합니다. return은 -> 로 대체 가능합니다.
```
Collections.sort(list, new Comparator<T>() {
    (T m1, T m2) -> m1.compareTo(m2)
    
})
```
new Comparator는 익명 함수가 되므로 생략이 가능하게 됩니다. 따라서 람다를 사용한 모습으로 바뀌게 됩니다. 
```
Collections.sort(list, (m1,m2)->m1.compareTo(m2))
```


# Reference
https://www.youtube.com/watch?v=aiWP9OaMT-c