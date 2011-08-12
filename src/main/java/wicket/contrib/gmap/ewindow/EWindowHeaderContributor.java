/*
 *
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.contrib.gmap.ewindow;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.behavior.HeaderContributor;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * Created by victor.konopelko
 */
public class EWindowHeaderContributor extends HeaderContributor
{
	private static final long serialVersionUID = 1L;

	// We have some custom Javascript.
	private static final ResourceReference EWINDOW_JS = new JavascriptResourceReference(
			EWindowHeaderContributor.class, "EWindow.js");

    // We have some custom CSS.
	private static final ResourceReference EWINDOW_CSS = new ResourceReference(
            EWindowHeaderContributor.class, "EWindow.css");

	public EWindowHeaderContributor()
	{
		super(new IHeaderContributor()
		{
			private static final long serialVersionUID = 1L;

			/**
			 * @see org.apache.wicket.markup.html.IHeaderContributor#renderHead(org.apache.wicket.markup.html.IHeaderResponse)
			 */
			public void renderHead(IHeaderResponse response)
			{
                response.renderCSSReference(EWINDOW_CSS);
                response.renderJavascriptReference(EWINDOW_JS);
			}
		});
	}
}
