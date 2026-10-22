// Bubble Sort Function - sortirBubble
let sortirBubble = (arrData) => {
  let n = arrData.length;
  // Outer loop: number of passes through array
  for(let i = 0; i < n; i++){
    // Inner loop: compare adjacent elements
    for(let j = 0; j < n - i - 1; j++){
      // If current element > next element → swap
      if(arrData[j] > arrData[j+1]){
        let temp = arrData[j];
        arrData[j] = arrData[j+1];
        arrData[j+1] = temp;
      }
    }
  }
  return arrData;
}

// Test case from lecture notes
console.log(sortirBubble([90, 2, 34, 1, 65, 7, 87]));