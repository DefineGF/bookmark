##### xml基本格式

```xml
<?xml version="1.0" encoding="utf-8" ?>
<roles>
    <role id="1">
        <name>Luffy</name>
        <sex>m</sex>
    </role>
    <role id="2">
        <name>Zoro</name>
        <sex>m</sex>
    </role>
    <role id="3">
        <name>Nami</name>
        <sex>f</sex>
    </role>
</roles>
```



##### DOM 解析

```java
private void DOMRead() throws IOException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse("src/version_1/roles.xml");
        NodeList roleList = document.getElementsByTagName("role");
        System.out.println("一共有" + roleList.getLength() + "个船员");
        
        for (int i = 0; i < roleList.getLength(); i++) {
            System.out.println("=================下面开始遍历第" + (i + 1) + "个船员=======================");

            Node role = roleList.item(i);
            NamedNodeMap attrs = role.getAttributes();
            System.out.println("第 " + (i + 1) + "个船员共有" + attrs.getLength() + "个属性");
            for (int j = 0; j < attrs.getLength(); j++) {
                Node attr = attrs.item(j);
                System.out.println("属性名：" + attr.getNodeName() + " ；属性值："+ attr.getNodeValue());
            }

            NodeList childNodes = role.getChildNodes();
            System.out.println("第" + (i+1) + "船员共有" + childNodes.getLength() + "个子节点");
            for (int k = 0; k < childNodes.getLength(); k++) {
                if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {//还有对象为Node.TEXT_NODE
                    System.out.print("第" + (k + 1) + "个节点的节点名：" + childNodes.item(k).getNodeName());
                    System.out.println("--节点值是：" + childNodes.item(k).getFirstChild().getNodeValue());
                }
            }
            System.out.println("======================结束遍历第" + (i + 1) + "船员的内容=================");
        }
    } catch (ParserConfigurationException | org.xml.sax.SAXException e) {
        e.printStackTrace();
    }
}
```

分析第一个船员情况：

```
=================下面开始遍历第1个船员=======================
第 1个船员共有1个属性
属性名：id--属性值1
第1船员共有5个子节点
第2个节点的节点名：name--节点值是：Luffy
第4个节点的节点名：sex--节点值是：m
======================结束遍历第1船员的内容=================
```

这个时候会发现第一个船员有5个子节点?拐回去再看xml文件因为子节点之间会有名为text的节点，具体为：

```xml
<role id="1">
        text1
        <name>Luffy</name>
        text2
        <sex>m</sex>
        text3
 </role>
```



##### DOM4

```java
 SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(new File("src/res/books.xml"));
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
                System.out.println("=====开始遍历某一本书=====");
                Element book = (Element) it.next();
                // 获取book的属性名以及 属性值
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs) {
                    System.out.println("属性名：" + attr.getName() + "--属性值："
                            + attr.getValue());
                }
                Iterator itt = book.elementIterator();
                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();
                    System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
                }
                System.out.println("=====结束遍历某一本书=====");
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block            e.printStackTrace();
        }
```

