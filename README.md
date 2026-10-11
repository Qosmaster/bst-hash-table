# BST and Hash Table

## How they work

I implemented both structures in Java.

BST is a tree. Smaller values go left, and larger values go right.
To insert or search, we follow these branches.
To delete a node, we remove it and reconnect the tree.
If it has two children, we replace its value with the smallest value on the right.

Hash Table uses a formula to choose a bucket.
Values with the same bucket index go into a linked list.
To insert, search, or delete, we work with this list.

## Time complexity

These times describe one operation.

| Structure | Operation | Best | Average | Worst |
|-----------|-----------|------|---------|-------|
| BST | Insert | O(1) | O(log n) | O(n) |
| BST | Search | O(1) | O(log n) | O(n) |
| BST | Delete | O(1) | O(log n) | O(n) |
| Hash Table | Insert | O(1) | O(1) | O(n) |
| Hash Table | Search | O(1) | O(1) | O(n) |
| Hash Table | Delete | O(1) | O(1) | O(n) |

O(1): only a few steps.
O(log n): the number of steps grows slowly.
O(n): we may check all values.

BST average time assumes random insertion order.
Its worst case happens when the tree becomes a long chain.

Hash Table average time assumes short bucket lists.
Its worst case happens when all values go into one bucket.
My insertion also checks the list for duplicates.

## Results

I used the same shuffled values for both structures.
Each test ran once. Times are in milliseconds for ALL n operations.
The Hash Table had n / 2 buckets, with two values per bucket when full.

| Elements | Structure | Insert | Search | Delete |
|----------|-----------|--------|--------|--------|
| 100 | BST | 8.8091 | 0.0717 | 0.0988 |
| 100 | Hash Table | 0.2495 | 0.0575 | 0.0617 |
| 1000 | BST | 0.2770 | 0.2231 | 0.2403 |
| 1000 | Hash Table | 0.5744 | 0.3278 | 0.4074 |
| 10000 | BST | 2.7247 | 2.3992 | 2.9032 |
| 10000 | Hash Table | 2.6111 | 1.8714 | 1.8760 |

Both structures found all values.

## Conclusion

Hash Table was faster with 100 and 10000 elements.
BST was faster with 1000 elements.

The first BST insertion was unusually slow.
Java starting up may explain this.

Tree shape, bucket size, and computer activity affect speed.
One test is not enough for exact results.

Hash Table is useful for fast searches.
BST is useful for keeping values in order.