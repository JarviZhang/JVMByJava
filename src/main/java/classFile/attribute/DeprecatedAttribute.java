package classFile.attribute;

import classFile.ClassReader;

public class DeprecatedAttribute extends AttributeInfo{
    int attribute_name_index;
    int attribute_length;

    @Override
    void readInfo(ClassReader reader) {
        //由于没有数据,所以是空的.
    }
}
