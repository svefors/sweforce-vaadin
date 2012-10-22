package sweforce.vaadin.util.string.interpolator;


/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.vaadin.data.Item;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.ConverterFactory;

import java.util.Locale;

//import org.apache.wicket.IConverterLocator;
//import org.apache.wicket.util.convert.IConverter;

/**
 * A {@link ItemPropertyInterpolator} converting values with {@link IConverter}s.
 *
 * @author svenmeier
 */
public class ConvertingPropertyVariableInterpolator extends ItemPropertyInterpolator
{
    private static final long serialVersionUID = 1L;

    private ConverterFactory converterFactory;

    private Locale locale;

    /**
     * Constructor.
     *
     * @param string
     *            a <code>String</code> to interpolate into
     * @param item
     *            the item to apply property expressions to
     * @param converterFactory
     *            the locator of converters
     * @param locale
     *            the locale for conversion
     */
    public ConvertingPropertyVariableInterpolator(final String string, final Item item,
                                                  ConverterFactory converterFactory, Locale locale)
    {
        super(string, item);

        this.converterFactory = converterFactory;
        this.locale = locale;
    }

    /**
     * Use an {@link IConverter} to convert the given value to a String.
     *
     * @param value
     *            the value, never {@code null}
     *
     * @return converted value
     */
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected String toString(Object value)
    {
        Converter converter = converterFactory.createConverter(String.class, value.getClass());
        return (String) converter.convertToPresentation(value, locale);

    }
}
