package com.pdd.pop.ext.fasterxml.jackson.databind.ser.std;

import java.io.*;
import java.nio.ByteBuffer;

import com.pdd.pop.ext.fasterxml.jackson.core.*;
import com.pdd.pop.ext.fasterxml.jackson.databind.*;
import com.pdd.pop.ext.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.pdd.pop.ext.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.pdd.pop.ext.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.pdd.pop.ext.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;

@SuppressWarnings("serial")
public class ByteBufferSerializer extends StdScalarSerializer<ByteBuffer>
{
    public ByteBufferSerializer() { super(ByteBuffer.class); }

    @Override
    public void serialize(ByteBuffer bbuf, JsonGenerator gen, SerializerProvider provider) throws IOException
    {
        // first, simple case when wrapping an array...
        if (bbuf.hasArray()) {
            gen.writeBinary(bbuf.array(), 0, bbuf.limit());
            return;
        }
        // the other case is more complicated however. Best to handle with InputStream wrapper.
        // But should we rewind it; and/or make a copy?
        ByteBuffer copy = bbuf.asReadOnlyBuffer();
        if (copy.position() > 0) {
            copy.rewind();
        }
        InputStream in = new ByteBufferBackedInputStream(copy);
        gen.writeBinary(in, copy.remaining());
        in.close();
    }

    @Override // since 2.9
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint)
        throws JsonMappingException
    {
        // 31-Mar-2017, tatu: Use same type as `ByteArraySerializer`: not optimal but has to do
        JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
        if (v2 != null) {
            v2.itemsFormat(JsonFormatTypes.INTEGER);
        }
    }
}
