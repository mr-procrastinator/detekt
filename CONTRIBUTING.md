# Contributing to detekt

- Read [this article](https://chris.beams.io/posts/git-commit/) before writing commit messages
- `detekt` itself should not report any new errors
- This repo uses tabs!
- Use idea-code-style.xml for coding style 
- Run `detektFormat` before commiting
- Make sure `when {` or `object : MyType { ... ` is on the same line as `=` eg. 
```kotlin
    fun stuff(x: Int) = when(x) {
        1..10 -> ...
        else -> ...
    }
```