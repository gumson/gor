/*
 *  BEGIN_COPYRIGHT
 *
 *  Copyright (C) 2011-2013 deCODE genetics Inc.
 *  Copyright (C) 2013-2019 WuXi NextCode Inc.
 *  All Rights Reserved.
 *
 *  GORpipe is free software: you can redistribute it and/or modify
 *  it under the terms of the AFFERO GNU General Public License as published by
 *  the Free Software Foundation.
 *
 *  GORpipe is distributed "AS-IS" AND WITHOUT ANY WARRANTY OF ANY KIND,
 *  INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 *  NON-INFRINGEMENT, OR FITNESS FOR A PARTICULAR PURPOSE. See
 *  the AFFERO GNU General Public License for the complete license terms.
 *
 *  You should have received a copy of the AFFERO GNU General Public License
 *  along with GORpipe.  If not, see <http://www.gnu.org/licenses/agpl-3.0.html>
 *
 *  END_COPYRIGHT
 */

package gorsat.Iterators

import org.gorpipe.model.genome.files.gor.Row
import org.gorpipe.model.gor.iterators.RowSource

class MultiRowSource(sourceList: List[RowSource]) extends RowSource {
  var rowsource: RowSource = _
  if (sourceList.length < 2) {
    rowsource = sourceList.head
  } else {
    val (lf, rf) = sourceList splitAt (sourceList.length / 2)
    rowsource = new DuoSourceGen(null, null, lf, rf, "NOT Files", null, "dummy gorRoot", null, null)
  }

  def hasNext: Boolean = rowsource.hasNext

  def next(): Row = rowsource.next()

  def setPosition(seekChr: String, seekPos: Int): Unit = rowsource.setPosition(seekChr, seekPos)

  def close: Unit = rowsource.close

  override def getHeader: String = rowsource.getHeader
}
