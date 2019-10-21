# Big O Notation

* Big O는 인풋에 따른 알고리즘의 복잡도를 나타냅니다.
* 항상 최악의 경우를 따지게 됩니다. 
* **인풋**과 **시간**과의 관계를 따집니다.
* 인풋은 시간, 공간 등이 될 수 있습니다.
* 인풋이 커질 때만 가치를 가집니다.

## O(1)
* input의 사이즈에 관계없이 일정한 실행을 합니다.
```
return element[0] = null;
```

## O(N)
* input의 사이즈에 따라 일정한 비율로 실행을 합니다. 
```
for(var i = 0; i < n; i++){
	return true;
}
```

## O(N^2)
* input의 사이즈에 따라 n^2의 비율로 실행을 합니다. 
```
for(var i = 0; i < n; i++){
	for(var j = 0; j < n; j++){
		return true;
	}
}
```
## O(2^N)
* input의 사이즈에 따라 두배로 실행을 합니다. 
```
int fibonacci(int i) {
	return fibonacci(i);
}
```

## O(2^N)
* input의 사이즈에 따라 두배로 실행을 합니다. 
* logarithm이라고도 합니다. 
```
var low = 0;
var high = n-1;

while low <= high Do
	mid = (low + high) /2;
	
	if array[mid] == value return mid;
	else if array[mid] < value low = mid + 1; 
	else if array[mid] > value high = mid - 1;
```