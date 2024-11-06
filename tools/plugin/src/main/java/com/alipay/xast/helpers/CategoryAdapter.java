/**
 * OWASP Benchmark Project
 *
 * <p>This file is part of the Open Web Application Security Project (OWASP) Benchmark Project For
 * details, please see <a
 * href="https://owasp.org/www-project-benchmark/">https:/owasp.org/www-project-benchmark/</a>.
 *
 * <p>The OWASP Benchmark is free software: you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation, version 2.
 *
 * <p>The OWASP Benchmark is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 *
 * @author David Anderson
 * @created 2021
 */
package com.alipay.xast.helpers;

import com.alipay.xast.Categories;
import com.alipay.xast.Category;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CategoryAdapter extends XmlAdapter<String, Category> {

    public String marshal(Category category) {
        return category.getId();
    }

    public Category unmarshal(String value) {
        return Categories.getById(value);
    }
}
