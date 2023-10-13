package classFile.attribute;

import classFile.ClassReader;

public class UnparsedAttribute extends AttributeInfo{
    private String attrName;
    private int attrLen;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen) {
        this.attrName = attrName;
        this.attrLen = attrLen;
    }

    @Override
    void readInfo(ClassReader reader) {
        info = reader.readBytes(attrLen);
    }
}
