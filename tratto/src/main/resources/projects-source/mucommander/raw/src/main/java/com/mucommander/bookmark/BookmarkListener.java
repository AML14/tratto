/*
 * This file is part of muCommander, http://www.mucommander.com
 *
 * muCommander is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * muCommander is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.mucommander.bookmark;


/**
 * Interface to be implemented by classes that wish to be notified when changes are made to the bookmarks list.
 * Those classes need to be registered to receive those events, this can be done by calling
 * {@link BookmarkManager#addBookmarkListener(BookmarkListener)}.
 *
 * @author Maxence Bernard
 */
public interface BookmarkListener {
	
    /**
     * This method is invoked when a bookmark has been added, removed or modified.
     */
    void bookmarksChanged();

}
