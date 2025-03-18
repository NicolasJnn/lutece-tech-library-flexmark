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

import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.emoji.EmojiExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gfm.users.GfmUsersExtension;
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import fr.paris.lutece.portal.service.editor.IRichTextContentParser;
import fr.paris.lutece.portal.service.editor.RichTextParsingException;

import java.util.Arrays;

/**
 * FlexMarkParser is a class that implements the IRichTextContentParser interface. It provides functionality to convert Markdown content to HTML using the
 * Flexmark library.
 */
public class FlexMarkParser implements IRichTextContentParser
{
    private String _strName = "MarkDown Parser";
    private String _strPrefix = "MD:";

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName( )
    {
        return _strName;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the name to set
     */
    public void setName( String name )
    {
        this._strName = name;
    }

    /**
     * Gets the prefix.
     *
     * @return the prefix
     */
    public String getPrefix( )
    {
        return _strPrefix;
    }

    /**
     * Sets the prefix.
     *
     * @param prefix
     *            the prefix to set
     */
    public void setPrefix( String prefix )
    {
        this._strPrefix = prefix;
    }

    /**
     * Converts the given Markdown content to HTML.
     *
     * @param content
     *            the Markdown content to be converted
     * @return the converted HTML content
     * @throws RichTextParsingException 
     *              if the parsing went wrong
     */
    public String parseContent( String content ) throws RichTextParsingException
    {
        try {
            // Configure Flexmark with additional options
            MutableDataSet options = new MutableDataSet( );
            options.set( Parser.EXTENSIONS, Arrays.asList( TablesExtension.create( ), // Support for tables
                    StrikethroughExtension.create( ), // Support for strikethrough text
                    AutolinkExtension.create( ), // Automatically create links from URLs
                    EmojiExtension.create( ), // Support for emojis
                    FootnoteExtension.create( ), // Support for footnotes
                    TaskListExtension.create( ), // Support for task lists
                    GfmUsersExtension.create( ), // Support for GitHub users
                    WikiLinkExtension.create( ) // Support for wiki links
            ) );
            options.set( HtmlRenderer.SOFT_BREAK, "<br />\n" ); // Render soft breaks as <br />
            options.set( WikiLinkExtension.LINK_ESCAPE_CHARS, "[]" ); // Escape characters for wiki links

            Parser parser = Parser.builder( options ).build( );
            HtmlRenderer renderer = HtmlRenderer.builder( options ).build( );

            // Parse and render the content
            String parsed_content = renderer.render( parser.parse( content ) );
            return parsed_content;
        }
        catch(Exception e) {
            throw new RichTextParsingException(e.getMessage(), e);
        }
    }
}
