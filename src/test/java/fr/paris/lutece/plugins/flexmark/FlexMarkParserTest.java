/*
 * Copyright (c) 2002-2025, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.flexmark;

import fr.paris.lutece.portal.service.editor.RichTextParsingException;
import junit.framework.TestCase;

/**
 * Unit test for FlexMarkParser.
 */
public class FlexMarkParserTest extends TestCase
{
    public void testGetHtmlFromMD( )
    {
        FlexMarkParser parser = new FlexMarkParser( );

        String markdown = "# Heading\n\nThis is a **bold** text.";
        String expectedHtml = "<h1>Heading</h1>\n<p>This is a <strong>bold</strong> text.</p>\n";

        String result = "";
        try {
            result = parser.parseContent( markdown );
        } catch (RichTextParsingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals( expectedHtml, result );
    }

    public void testGetHtmlFromMDWithTable( )
    {
        FlexMarkParser parser = new FlexMarkParser( );

        String markdown = "| Header1 | Header2 |\n|---------|---------|\n| Cell1   | Cell2   |";
        String expectedHtml = "<table><thead><tr><th>Header1</th><th>Header2</th></tr></thead><tbody><tr><td>Cell1</td><td>Cell2</td></tr></tbody></table>";

        String result = "";
        try {
            result = parser.parseContent( markdown );
        } catch (RichTextParsingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals( expectedHtml, result.replace( "\n", "" ) );
    }
}
