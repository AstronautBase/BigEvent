# big-event开发-文章分类

## 新增文章分类

- 使用validation完成参数校验
- 在service层为Category的createUser createTime updateTime赋值

## 文章分类列表

### 更改日期格式-JsonFormat

```java
@Data
public class Category {
    private Integer id;//主键ID
    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间
}
```

## 获取文章分类详情

## 更新文章分类

@NotEmpty - 必须传，且字符串不能为空

@NotNull - 仅需传送

### 分组校验

1. 定义分组
2. 定义校验时指定归属的分组
3. 校验时指定要校验的分组

```java
public Result add(@RequestBody @Validated(Category.Add.class) Category category)
public Result update(@RequestBody @Validated(Category.Update.class) Category category)
```

```java
@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
    @NotEmpty
    private String categoryName;//分类名称
    @NotEmpty
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    // 如果某个校验项没有指定分组，默认属于Default分组 [此例中的categoryName&categortAlias]
    // 分组之间可以继承，A extends B，那么A中拥有B中所有的校验项

    public interface Add extends Default {

    }

    public interface Update extends Default {

    }
}
```

## 删除文章分类

