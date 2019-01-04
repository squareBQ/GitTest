## Git如何撤销一次merge

#### 方法一：`reset`到`merge`前的版本，然后再重新做接下来的操作，要求每个合作者都晓得怎么将本地的`HEAD`都回滚回去：
	$ git checkout 【行merge操作时所在的分支】
	$ git reset --hard 【merge前的版本号】
	
#### 方法二：当`merge`以后还有别的操作和改动时，`git`正好也有办法能撤销`merge`，用`git regert`
	$ git revert -m 【要撤销的那条merge线的编号，从1开始计算(怎么看是哪条线是几啊？)】 【merge前的版本号】
	Finished one revert.
	[master 88edd6d] Revert "Merge branch 'jk/post-checkout'"
 	1 files changed, 0 insertions(+), 2 deletions(-)
 	
 这样会创建新的`commit`来抵消对应的`merge`操作，而且以后`git merge`【那个编号所代表的分支】会提示
 	`Already up-to-date.`
 因为使用方法二会让`git`误以为这个分支的东西都是咱们不想要的。
 
#### 方法三：怎么撤销方法二
 
 	$ git revert 【方法二撤销merge时提交的commit的版本号】
 	Finished one revert.
 	[master 268e243] Revert "Revert "Merge branch 'jk/post-checkout'""
 	1 files changed, 2 insertions(+), 0 deletions(-)
 这样就行了，可以正常`merge`了，不过可能会有很多冲突哦！！
 
 	$ git merge jk/post-checkout
 	Auto-merging test.txt
 	Merge made by recursive.
 	1 files changed, 1 insertions(+), 0 deletions(-)
 
 --------
 最后的最后，还是觉得上面那些都麻烦爆了的，请看下面：
 
 	Local History -> Show History
 点开后出现一个窗口，可以看到所有的本地改动。找到适合的那个，按上面那个 `Revert` 按钮，也就是紫色带弯曲的小箭头就回去了。在文件量不大的时候十分管用，强烈推荐。