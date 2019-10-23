# Big O Notation

## Asymptotic Analysis

- 사전적인 정의로 Asymptotic은 **점근적 분석**이라는 개념입니다.
- 곡선이 x나 y축 방향으로 점근하지만 무한대로 가더라도 접근하지 못하는 상태를 말합니다.
- 알고리즘에서는 이런 접근이 매우 중요합니다.
- Big O의 개념은 **input의 크기가 커질 때마다 얼만큼의 시간이 소요되는가** 입니다.
- 즉, input의 크기가 점점 커짐에 따라 소요되는 시간이 얼만큼 되는지 아는 것이 중요합니다.
- 예를들어 O(n)에서 n이 input이 됩니다.
- n이 점점 커지는 것을 상상해 봅시다.
  ```
   n = 1		O(1) = 1
   n = 2		O(2) = 2
   n = 3		O(3) = 3
   ...
   n = 1000		O(1000) = 1000
  ```
- input의 값이 늘어날 때마다 일정한 기울기로 시간이 증가함을 알 수 있습니다.
- O(n^2)의 경우를 보겠습니다.
- ```
   n = 1		O(1) = 1
   n = 2		O(2) = 4
   n = 3		O(3) = 9
   ...
   n = 1000		O(1000) = 1000000
  ```
- input의 값이 늘어날 때마다 시간은 n^2 값으로 매우 가파르게 증가하게 됩니다.
- 보다시키 input이 1일 떄는 두 함수의 시간은 1로 차이가 없습니다. 그러므로 input이 작은 상태에서 두 함수를 비교하는 것은 의미가 없습니다.
- 하지만 **n이 무한대로 커진다면** 두 함수의 걸리는 시간은 매우 극적이로 차이가 나게 됩니다.
- 즉, input의 값이 매우 커졌을 때 **비로소 함수의 특징**이 나타날 수 있게 됩니다.
- 이처럼 Asymptotic Anaysis는 input이 매우 커졌을 때, 함수(O(n), O(n^2))에 따라 시간이 얼마나 차이가 나는지 예측하는 분석 방법입니다.

## Worst, Average, Best Case

- 하지만 위의 방법이 모든 것을 설명할 수는 없습니다.
- 앞서 input 값이 작은 경우, 시간이 더 걸릴 수도 있고 아닐 수도 있습니다.
- 그러므로 다양한 경우의 수를 찾아봐야 합니다.
- 보통 세가지의 경우의 수를 찾습니다.

  - Worst
  - Average
  - Best

- linear search를 예를들어 보겠습니다.

```
1, 2, 4, 5, 6
```

1. Worst (보통 발생)
   - 숫자 6을 찾고 싶다면 어떻게 해야할까요?
   - 하나씩 검사해야 할 것입니다.
   - O(n)
   - input에 대해 최대한 많은 스텝으로 실행하는 함수
   - 실제 프로그래밍에서 가장 고려를 많이해야하는 부분입니다.
   - 왜냐하면 최악의 시나리오에서 성공한다면 그 함수는 평균적으로나 최소한의 경우에서도 제 시간에 끝난다는 것을 보장할 수 있기 때문입니다.
2. Average (때떄로 발생)
   - 2,4,5를 찾는 경우가 됨
   - 평균은 모든 경우의 수를 더하여 개수로 나누어주는 것일 겁니다.
   - 1 + 2 + ... N
   - 일반화 시킨다면 N(N+1)/2 이고 이를 (N+1)로 나누면 결국 N/2만 남습니다.
   - O(n)
3. Best case (발생 안한다보면됨)
   - 1
   - O(1)
   - input에 대해 최소한의 스텝으로 실행하는 함수
   - 대부분의 알고리즘에서는 고려하지 않는 부분입니다.
   - 평균이나 최악의 경우를 개선하는 알고리즘이 더욱 의미가 있기 때문입니다.

- best와 average는 사용되지 않습니다 .
- best인 경우는 input이 작아 아까 말했듯이 다른 알고리즘과 비교해서 비슷한 행위를 나타내기 때문에 정보가 부족합니다.
- average의 경우는 다양한 경우의 수들이 있기 때문에 예측하기가 힘듦니다.
- worst 케이스는 input의 양이 매우 클 때 발생합니다. 이는 asymptotic analysis의 연장으로 input이 매우 많을 때 그 함수의 특징을 알 수 있기 때문입니다.
- 문제를 해결할 때 worst 케이스가 작은 함수(O())를 선택하는 것이 현명한 방법이 됩니다.
- 그리하여 worst-case analysis가 나오게 됩니다.
- 흔히 시간 복잡도 표를 볼 수 있게 되는데 여기서 볼 것은 Worst case만 보시면 됩니다.
- 즉, 우리가 해결해야할 문제에서 가장 최악의 케이스 중 그나마 가장 빠른 알고리즘을 보고 선택하여야 합니다.

### Bubble sort

- comparison
- swap

```
20, 5, 3, 7, 8
j  j+1       i
```

```
for(int i = n)    // 제일 큰 숫자를 가장 뒤로 보낼꺼야
  for(int j = 1; j < i -1)  // 시작부터 검사해서 가장 큰 값을 제일 뒤로 보낼 꺼야
  if(A[j] > A[j+1])       // comparison
    swap(A[j], A[j+1])    // swap
```

1. best case

```
1,2,3,4,5,6
```

```
if(A[j] > A[j+1]) 이 부분이 항상 false이므로 swap이 되지 않는다
```

2. worst case

```
6,5,4,3,2,1
```

```
if(A[j] > A[j+1])       항상 true
    swap(A[j], A[j+1])  항상 swap
```

Gausian formular
1 + 2 + ... + n = n(n+1)/2

1 + 1 + ... + 1
1 ~ (i -1) + 1
lower bound upper bound

i=2 ~ n
n(n+1)/2-n

3. average case
   swap이 될 가능성
   0, 1으로 각 1/2
   E(x) = (0, 1)\* 1/2 = 1/2
   swap이 될 평균적인 확률은 50%라고 볼수 있다.
   (n-1)n/2 = swap이 될 모든 경우의 수

### Insertion sort

```

```

comparison

- 아이템을 비교할 때 (while, for)
  move
- 아이템을 움직일 때 생길 때

best case

```
1 2 3 4 5 6
```

comparison

- 외부의 루프가 도는 동안 내부 loop는 돌지 않습니다.
-

### Quick sort

pivot
partioning

Algorithm A vs Algorithm B
n^2 2^n

# Big O

- 간단하게 만들어 버리는 방식
- 3n-1 = O(n)
- 함수가 어떤 행위를 가지는지만 보는 것

- Big O는 인풋에 따른 알고리즘의 복잡도를 나타냅니다.
- 항상 최악의 경우를 따지게 됩니다.
- **인풋**과 **시간**과의 관계를 따집니다.
- 인풋은 시간, 공간 등이 될 수 있습니다.
- 인풋이 커질 때만 가치를 가집니다.

## O(1)

- input의 사이즈에 관계없이 일정한 실행을 합니다.

```
return element[0] = null;
```

## O(N)

- input의 사이즈에 따라 일정한 비율로 실행을 합니다.

```
for(var i = 0; i < n; i++){
	return true;
}
```

## O(N^2)

- input의 사이즈에 따라 n^2의 비율로 실행을 합니다.

```
for(var i = 0; i < n; i++){
	for(var j = 0; j < n; j++){
		return true;
	}
}
```

## O(2^N)

- input의 사이즈에 따라 두배로 실행을 합니다.

```
int fibonacci(int i) {
	return fibonacci(i);
}
```

## O(2^N)

- input의 사이즈에 따라 두배로 실행을 합니다.
- logarithm이라고도 합니다.

```
var low = 0;
var high = n-1;

while low <= high Do
	mid = (low + high) /2;

	if array[mid] == value return mid;
	else if array[mid] < value low = mid + 1;
	else if array[mid] > value high = mid - 1;
```

logarithm

- log2(8) = 3
- 도대체 무슨 말일까
- 예를들어 [1,2,3,4,5,6,7,8]이라는 배열이 있다고 가정해보자
- 배열에는 총 8개의 숫자가 있다.
- 이를 binary sort를 해보자
- 즉, 계속 두 쪽으로 나누어 보자

```
[1,2,3,4,5,6,7,8]
							1
[1,2,3,4][5,6,7,8]
							2
[1,2][3,4][5,6][7,8]
							3
[1][2][3][4][5][6][7][8]
```

- 숫자 (8)개인 배열을 계속 둘(2)로 나누었다.
- 그러면 총 세단계(3)를 통해 기본이 되는 요소로 나누어진다.
- log2(8) = 3의 의미는
- 크기가 (8)인 input을 계속 둘(2)로 나누는데 걸리는 시간은 총 (3)이 된다 는 뜻이다.
- binary search가 log2(n)가 되는 이유이다
- 무언가를 계속 2로 나누는 행위가 반복되는 알고리즘 (바이너리 서치, 트리 등)의 Big O가 항상 log2(n)의 모양이 되는 이유이다.

## Space complexity

- 스택같은 얼마나 콜을 부를 것인가.
- fib(4)
  - 네번 들어간다.
  - O(n)

```
fib(4)							1
fib(3) fib(2)					2
fib(2) fib(1) fib(1) fib(0)		3
fib(0) fib(1) fib(0) fib(0)		4
```

인터뷰에서

- array sort
- log(n) 원함
  그러면 뭘 써야될것인가?
- recursion
- complete search 등을 사용하여 문제를 해결할 것이다!

## reference

- bigocheatsheet.com
- https://www.youtube.com/watch?v=waPQP2TDOGE
- https://www.youtube.com/watch?v=m8B4OYJy-jQ
- https://www.youtube.com/watch?v=GIgHVrr4CJE
